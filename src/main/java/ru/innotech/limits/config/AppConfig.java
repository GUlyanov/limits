package ru.innotech.limits.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.innotech.limits.entities.Limit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.innotech.limits.repositories.LimitRepo;

@Configuration
@EnableConfigurationProperties({LimitProps.class})
@EnableScheduling
public class AppConfig {
    // Установить дневной лимит всем пользователям (внедрение приложения)
    @Bean
    public CommandLineRunner dataLoader(
            LimitRepo limitRepo, LimitProps limitProps) {
        return args -> {
            limitRepo.deleteAll();
            Limit limit;
            for (long i = 0L; i < limitProps.getUserCount(); i++) {
                limit = new Limit(i, limitProps.getDayValue());
                limitRepo.save(limit);
            }
        };

    }
}
