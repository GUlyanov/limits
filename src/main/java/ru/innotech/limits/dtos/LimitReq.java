package ru.innotech.limits.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LimitReq {
    private Long userId;
    private BigDecimal summa;
    private Boolean minus;
}
