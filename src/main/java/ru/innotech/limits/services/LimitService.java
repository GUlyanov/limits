package ru.innotech.limits.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innotech.limits.config.LimitProps;
import ru.innotech.limits.dtos.LimitReq;
import ru.innotech.limits.dtos.LimitResp;
import ru.innotech.limits.dtos.LimitRespSmall;
import ru.innotech.limits.entities.Limit;
import ru.innotech.limits.exceptions.InsufficientLimitValueEx;
import ru.innotech.limits.exceptions.UserNotExistsEx;
import ru.innotech.limits.repositories.LimitRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LimitService {
    private final LimitRepo limitRepo;
    private final LimitProps limitProps;

    public LimitService(LimitRepo limitRepo, LimitProps limitProps) {
        this.limitRepo = limitRepo;
        this.limitProps = limitProps;
    }

    // Создать лимит для нового пользователя
    @Transactional
    public Limit createLimit(Long userId) {
        Limit limit = new Limit(userId, limitProps.getDayValue());
        limitRepo.save(limit);
        return limit;
    }

    // Получить текущее значение лимита для пользователя, если лимит не существует, бросить исключение (не нужно)
    public BigDecimal getLimitValueForUser(Long userId) {
        return limitRepo.findValueByUserId(userId)
                .orElseThrow(() -> new UserNotExistsEx(null, userId));
    }

    // Получить лимит для пользователя, если лимит не существует, то создать его
    @Transactional
    public Limit getLimitForUserOrCreate(Long userId) {
        return limitRepo.findLimitByUserId(userId).orElseGet(() -> createLimit(userId));
    }

    // Модифицировать лимит пользователя
    @Transactional
    public LimitResp updLimit(LimitReq limitReq) {
        Long userId = limitReq.getUserId();
        BigDecimal summa = limitReq.getSumma();

        // Проверить наличие пользователя с userId из запроса, если лимита нет, то создать его
        Limit limit = getLimitForUserOrCreate(userId);

        // Текущее значение лимита у пользователя
        BigDecimal valueOld = limit.getValue();
        BigDecimal valueNew;

        // Если это списание лимита
        if (limitReq.getMinus()) {
            // Проверить достаточность лимита у пользователя
            if (summa.compareTo(valueOld) > 0)
                throw new InsufficientLimitValueEx(null, userId, valueOld, summa);
            // Списать сумму платежа из лимита, если достаточно
            valueNew = valueOld.subtract(summa);
        } else {
            // Если это восстановление лимита - восстановить лимит
            valueNew = valueOld.add(summa);
        }
        limit.setValue(valueNew);
        limitRepo.save(limit);

        return new LimitResp(userId, valueOld, valueNew);
    }

    // Получить список лимитов, меньших максимального
    public List<LimitRespSmall> getLimitsUpdated() {
        BigDecimal dayLimit = limitProps.getDayValue();
        List<Limit> limitList = limitRepo.findLimitsUpdated(dayLimit);
        return limitList.stream()
                .map(limit -> new LimitRespSmall(limit.getUserId(), limit.getValue()))
                .toList();
    }

    // Сброс дневных лимитов для пользователей
    @Scheduled(cron = "${cron.expression}", zone = "${cron.zone}")
    @Transactional
    public void resetLimits() {
        limitRepo.resetLimits(limitProps.getDayValue());
    }
}
