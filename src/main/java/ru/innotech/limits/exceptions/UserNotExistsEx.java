package ru.innotech.limits.exceptions;

import lombok.Getter;

@Getter
public class UserNotExistsEx extends RuntimeException {
    private final Long userId;

    public UserNotExistsEx(String message, Long userId) {
        super(message);
        this.userId = userId;
    }
}
