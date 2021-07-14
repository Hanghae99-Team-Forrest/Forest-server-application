package com.forrest.server.util.exception;

/**
 * @Created by Bloo
 * @Date: 2021/07/14
 */


public class UnauthorizedException extends RuntimeException {
    private static final String MESSAGE = "로그인이 필요한 서비스 입니다.";
    public UnauthorizedException () {
        super(MESSAGE);
    }
}
