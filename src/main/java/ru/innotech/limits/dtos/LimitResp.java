package ru.innotech.limits.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LimitResp {
    private Long userId;
    private BigDecimal valueOld;
    private BigDecimal valueNew;
}
