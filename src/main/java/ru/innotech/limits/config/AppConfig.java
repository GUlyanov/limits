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
}
