package com.busbuddy.commuterservice.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TechnicalException extends RuntimeException {
    private final String code;

    public TechnicalException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
