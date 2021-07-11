package com.forrest.server.web.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponse {

    private int code;
    private String message;

    public static ErrorResponse of ( HttpStatus status, String  message) {
        return new ErrorResponse(status.value(), message );
    }

    public static ErrorResponse of ( HttpStatus status, FieldError fieldError ) {
        if ( fieldError != null ) {
            return new ErrorResponse(status.value(), fieldError.getDefaultMessage() );
        }
        return new ErrorResponse(status.value(), "Invalid Param" );
    }
}
