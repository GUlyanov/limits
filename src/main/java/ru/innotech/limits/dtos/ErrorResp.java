package ru.innotech.limits.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResp {
    Long userId;
    String message;
}
