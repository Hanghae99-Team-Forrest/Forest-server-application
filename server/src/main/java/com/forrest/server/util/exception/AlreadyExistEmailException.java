package com.forrest.server.util.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */

@Slf4j
public class AlreadyExistEmailException extends RuntimeException {
    private static final String MESSAGE = "이미 등록된 Email 입니다.";
    public AlreadyExistEmailException () {
        super(MESSAGE);
    }
}
