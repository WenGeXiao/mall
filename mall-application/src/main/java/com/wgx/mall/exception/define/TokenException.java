package com.wgx.mall.exception.define;


public class TokenException extends RuntimeException {
    String message;

    public TokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
