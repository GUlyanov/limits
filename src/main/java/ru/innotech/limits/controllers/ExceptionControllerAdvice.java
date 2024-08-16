package ru.innotech.limits.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.innotech.limits.exceptions.InsufficientLimitValueEx;
import ru.innotech.limits.exceptions.UserNotExistsEx;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UserNotExistsEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String excUserNotExistsExHandler(UserNotExistsEx ex) {
        String sMess = "Пользователь с ид <%d> не существует";
        sMess = sMess.formatted(ex.getUserId());
        return sMess;
    }

    @ExceptionHandler(InsufficientLimitValueEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String excInsufficientLimitValueExHandler(InsufficientLimitValueEx ex) {
        String sMess = "Лимит пользователя c ид = <%d> равный <%.2f> недостаточен для списания суммы <%.2f>";
        sMess = sMess.formatted(ex.getUserId(), ex.getValueOld(), ex.getDelta());
        return sMess;
    }
}
