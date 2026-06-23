
package com.vai.unified.auth.googleAuth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class GoogleAuthCreate {

    public static String createSecretKey() {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        String secretKey = key.getKey();

        System.out.println("Secret key generated: " + secretKey);
        return secretKey;
    }

    /**     * Validates the 6-digit code from Google Authenticator app     * @param secretKey - The secret key stored in your system     * @param code - The 6-digit code from the authenticator app     * @return true if code is valid, false otherwise     */
    public static boolean validateCode(String secretKey, int code) {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        return googleAuthenticator.authorize(secretKey, code);
    }
}