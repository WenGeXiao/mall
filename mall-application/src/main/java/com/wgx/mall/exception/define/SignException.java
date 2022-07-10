package com.wgx.mall.exception.define;

public class SignException extends RuntimeException{
    private String message;

    public SignException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
