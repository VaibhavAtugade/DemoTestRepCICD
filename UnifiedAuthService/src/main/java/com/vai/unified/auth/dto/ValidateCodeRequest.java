package com.vai.unified.auth.controller;

public class ValidateCodeRequest {
    private String secretKey;
    private int code;

    public ValidateCodeRequest() {}

    public ValidateCodeRequest(String secretKey, int code) {
        this.secretKey = secretKey;
        this.code = code;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}