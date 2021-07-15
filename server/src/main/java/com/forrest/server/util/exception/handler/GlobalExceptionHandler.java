package com.forrest.server.util.exception.handler;

import com.forrest.server.util.exception.AlreadyExistEmailException;
import com.forrest.server.util.exception.CategoryNotFoundException;
import com.forrest.server.util.exception.FileConvertFailException;
import com.forrest.server.util.exception.ImageDeleteFailException;
import com.forrest.server.util.exception.PostNotFoundException;
import com.forrest.server.web.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    /**
     * 이메일 중복 체크
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistEmailException.class)
    public ErrorResponse handleAlreadyExistEmailException(AlreadyExistEmailException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST , ex.getMessage());
    }

    /**
     * Invalid Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST , ex.getFieldError());
    }

    /**
     *  카테고리 조회 실패
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException( CategoryNotFoundException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * 게시글 조회 실패
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PostNotFoundException.class)
    public ErrorResponse handlePostNotFoundException( PostNotFoundException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * 이미지 삭제 실패
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageDeleteFailException.class)
    public ErrorResponse handleImageDeleteFailException( ImageDeleteFailException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * MultiPartFile -> File 변환 실패
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FileConvertFailException.class)
    public ErrorResponse handleFileConvertFailException(FileConvertFailException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
