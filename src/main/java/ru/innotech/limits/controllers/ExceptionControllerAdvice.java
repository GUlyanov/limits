package ru.innotech.limits.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.innotech.limits.dtos.ErrorResp;
import ru.innotech.limits.exceptions.InsufficientLimitValueEx;
import ru.innotech.limits.exceptions.UserNotExistsEx;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UserNotExistsEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResp excUserNotExistsExHandler(UserNotExistsEx ex) {
        String errMess = "Пользователь с ид <%d> не существует";
        errMess = errMess.formatted(ex.getUserId());
        return new ErrorResp(ex.getUserId(), errMess);
    }

    @ExceptionHandler(InsufficientLimitValueEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResp excInsufficientLimitValueExHandler(InsufficientLimitValueEx ex) {
        String errMess = "Лимит пользователя c ид = <%d> равный <%.2f> недостаточен для списания суммы <%.2f>";
        errMess = errMess.formatted(ex.getUserId(), ex.getValueOld(), ex.getDelta());
        return new ErrorResp(ex.getUserId(), errMess);
    }
}
