package com.forrest.server.util.exception.handler;

import com.forrest.server.util.exception.AlreadyExistEmailException;
import com.forrest.server.util.exception.CategoryNotFoundException;
import com.forrest.server.util.exception.UnauthorizedException;
import com.forrest.server.web.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistEmailException.class)
    public ErrorResponse handleAlreadyExistEmailException(AlreadyExistEmailException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST , ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST , ex.getFieldError());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException ( UnauthorizedException  ex) {
        return ErrorResponse.of(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException( CategoryNotFoundException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
