package com.forrest.server.util.exception;

/**
 * @Created by Bloo
 * @Date: 2021/07/15
 */


public class FileConvertFailException extends RuntimeException {
    private static final String MESSAGE = "MultipartFile -> File로 전환이 실패했습니다.";
    public FileConvertFailException () {
        super(MESSAGE);
    }
}
