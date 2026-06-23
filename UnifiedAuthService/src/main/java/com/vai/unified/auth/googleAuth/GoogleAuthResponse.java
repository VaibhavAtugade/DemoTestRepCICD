package com.vai.unified.auth.googleAuth;


public class GoogleAuthResponse {
    private String secretKey;
    private String qrCodeUrl;
    private boolean success;

    public GoogleAuthResponse(String secretKey, String qrCodeUrl, boolean success) {
        this.secretKey = secretKey;
        this.qrCodeUrl = qrCodeUrl;
        this.success = success;
    }

    public GoogleAuthResponse() {}

    // Getters and Setters
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}