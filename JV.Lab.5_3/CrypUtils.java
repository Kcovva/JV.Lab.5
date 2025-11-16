package org.example;

public class CrypUtils {

    public static String encrypt(String plain, String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Ключ не може бути порожнім");
        }
        StringBuilder sb = new StringBuilder(plain.length());
        int keyLen = key.length();
        for (int i = 0; i < plain.length(); i++) {
            int p = plain.charAt(i);
            int k = key.charAt(i % keyLen);
            int c = (p + k) & 0xFFFF;
            sb.append((char) c);
        }
        return sb.toString();
    }

    public static String decrypt(String cipher, String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Ключ не може бути порожнім");
        }
        StringBuilder sb = new StringBuilder(cipher.length());
        int keyLen = key.length();
        for (int i = 0; i < cipher.length(); i++) {
            int c = cipher.charAt(i);
            int k = key.charAt(i % keyLen);
            int p = (c - k) & 0xFFFF;
            sb.append((char) p);
        }
        return sb.toString();
    }
}
