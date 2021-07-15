package com.forrest.server.util.exception;

/**
 * @Created by Bloo
 * @Date: 2021/07/14
 */


public class CategoryNotFoundException extends RuntimeException {
    private static final String MESSAGE = "잘못된 Category ID 입니다.";
    public CategoryNotFoundException () {
        super(MESSAGE);
    }
}
