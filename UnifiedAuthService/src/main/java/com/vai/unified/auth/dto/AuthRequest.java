package com.vai.unified.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String userId;
    private String password;
    private String appName;
    private String deviceImei;
}
