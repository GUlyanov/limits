package ru.innotech.limits.exceptions;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InsufficientLimitValueEx extends RuntimeException {
    Long userId;
    BigDecimal valueOld;
    BigDecimal delta;

    public InsufficientLimitValueEx(String message, Long userId, BigDecimal valueOld, BigDecimal delta) {
        super(message);
        this.userId = userId;
        this.valueOld = valueOld;
        this.delta = delta;
    }
}
