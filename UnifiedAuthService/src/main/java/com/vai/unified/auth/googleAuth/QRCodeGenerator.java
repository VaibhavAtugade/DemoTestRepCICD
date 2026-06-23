
package com.vai.unified.auth.googleAuth;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class QRCodeGenerator {

    public static String generateQRCodeImage(String secretKey, String email, String appName) throws Exception {
        String otpAuthUrl = "otpauth://totp/" + appName + ":" + email + "?secret=" + secretKey + "&issuer=" + appName;
        String qrCodeUrl = "https://chart.googleapis.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/UnifiedApp:" + secretKey;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthUrl, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        byte[] qrCodeImage = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(qrCodeImage);

        return  qrCodeUrl ;//"data:image/png;base64," + base64Image;
    }
}