package ru.innotech.limits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.innotech.limits.config.LimitProps;

@SpringBootApplication
@EnableConfigurationProperties({LimitProps.class})
@EnableScheduling
public class AppLimits {
    public static void main(String[] args) {
        SpringApplication.run(AppLimits.class, args);
    }
}
