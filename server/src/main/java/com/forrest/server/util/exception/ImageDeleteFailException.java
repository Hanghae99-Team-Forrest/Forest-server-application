package com.forrest.server.util.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by Bloo
 * @Date: 2021/07/15
 */

@Slf4j
public class ImageDeleteFailException extends RuntimeException {
    private static final String MESSAGE = "파일 삭제에 실패 했습니다.";
    public ImageDeleteFailException () {
        super(MESSAGE);
    }
}
