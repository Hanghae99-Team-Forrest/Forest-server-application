package com.forrest.server.util.exception;

/**
 * @Created by Bloo
 * @Date: 2021/07/14
 */


public class PostNotFoundException extends RuntimeException{
    private static final String MESSAGE = "잘못된 게시글 ID 입니다.";
    public PostNotFoundException () {
        super(MESSAGE);
    }
}
