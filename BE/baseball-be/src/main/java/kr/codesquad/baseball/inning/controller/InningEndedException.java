package kr.codesquad.baseball.inning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "이미 종료된 이닝")
public class InningEndedException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "이미 종료된 이닝입니다. ";

    public InningEndedException(String message) {
        super(DEFAULT_MESSAGE + message);
    }

    public InningEndedException(String message, Throwable cause) {
        super(DEFAULT_MESSAGE + message, cause);
    }
}
