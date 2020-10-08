package com.dev.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(salt);
            byte[] hashedPass = messageDigest.digest(password.getBytes());
            for (byte b: hashedPass) {
                hashedPassword.append(String.format("%02x",b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can`t hash user password", e);
        }
        return hashedPassword.toString();
    }
}
