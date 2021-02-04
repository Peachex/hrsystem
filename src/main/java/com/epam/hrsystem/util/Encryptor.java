package com.web.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {
    public static String encrypt(String password) {
        String salt = BCrypt.gensalt();
        String hashedValue = BCrypt.hashpw(password, salt);
        return hashedValue;
    }

    public static boolean check(String password, String hashedValue) {
        boolean result = BCrypt.checkpw(password, hashedValue);
        return result;
    }
}
