package ru.innotech.limits.controllers;

import org.springframework.web.bind.annotation.*;
import ru.innotech.limits.dtos.LimitReq;
import ru.innotech.limits.dtos.LimitResp;
import ru.innotech.limits.dtos.LimitRespSmall;
import ru.innotech.limits.services.LimitService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/limits")
public class LimitController {
    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    // изменить лимит клиенту после платежа (списать после успешного или восстановить после неудачного)
    @PutMapping("/user/update")
    public LimitResp handUpdLimit(@RequestBody LimitReq limitReq) {
        return limitService.updLimit(limitReq);
    }

    // получить текущий лимит пользователя (если лимит не существует, то создать дневной лимит для этого пользователя)
    @GetMapping("user/{userId}")
    public BigDecimal handGetLimitForUserOrCreate(@PathVariable Long userId) {
        return limitService.getLimitForUserOrCreate(userId).getValue();
    }

    // получить текущий лимит пользователя (если лимит не существует, то вернуть ошибку)
    @GetMapping("user/nocreate/{userId}")
    public BigDecimal handGetLimitValueForUser(@PathVariable Long userId) {
        return limitService.getLimitValueForUser(userId);
    }

    // получить список пользователей и их лимитов, которые меньше максимального дневного лимита
    @GetMapping("/updated")
    public List<LimitRespSmall> handGetLimitsUpdated() {
        return limitService.getLimitsUpdated();
    }

}
