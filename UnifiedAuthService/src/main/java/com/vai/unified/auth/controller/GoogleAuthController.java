
package com.vai.unified.auth.controller;

import com.vai.unified.auth.dto.ValidationResponse;
import com.vai.unified.auth.googleAuth.GoogleAuthCreate;
import com.vai.unified.auth.googleAuth.GoogleAuthResponse;
import com.vai.unified.auth.googleAuth.QRCodeGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/google")
@CrossOrigin(origins = "*")
public class GoogleAuthController {

    @PostMapping("/generate-secret")
    public ResponseEntity<?> generateGoogleAuthSecret(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "appName", required = true) String appName) {

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body("Missing or empty required parameter: email");
        }
        if (appName == null || appName.isBlank()) {
            return ResponseEntity.badRequest().body("Missing or empty required parameter: appName");
        }

        try {
            String secretKey = GoogleAuthCreate.createSecretKey();
            String qrCodeBase64 = QRCodeGenerator.generateQRCodeImage(secretKey, email, appName);

            GoogleAuthResponse response = new GoogleAuthResponse(secretKey, qrCodeBase64, true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to generate secret/QR: " + e.getMessage());
        }
    }

    @PostMapping("/validate-code")
    public ResponseEntity<?> validateAuthCode(@RequestBody com.vai.unified.auth.controller.ValidateCodeRequest request) {
        if (request.getSecretKey() == null || request.getSecretKey().isBlank()) {
            return ResponseEntity.badRequest().body("Missing required parameter: secretKey");
        }
        if (String.valueOf(request.getCode()).length() != 6) {
            return ResponseEntity.badRequest().body("Code must be exactly 6 digits");
        }

        try {
            boolean isValid = GoogleAuthCreate.validateCode(request.getSecretKey(), request.getCode());

            if (isValid) {
                return ResponseEntity.ok(new ValidationResponse("Code is valid", true));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ValidationResponse("Code is invalid or expired", false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Validation error: " + e.getMessage());
        }
    }
}