package ru.innotech.limits.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "limits")
@Getter
@Setter
public class LimitProps {
    private BigDecimal dayValue;
    private Integer userCount;
}
