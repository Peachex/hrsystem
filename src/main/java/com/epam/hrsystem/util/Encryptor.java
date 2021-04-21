package com.epam.hrsystem.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class used to encrypt and check already encrypted passwords.
 *
 * @author Aleksey Klevitov
 */
public final class Encryptor {
    private Encryptor() {
    }

    /**
     * Encrypts a given password.
     *
     * @param password String object of the password to be encrypted.
     * @return String object of encrypted password.
     */
    public static String encrypt(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    /**
     * Checks if an unencrypted password equals to an encrypted one.
     *
     * @param password    String object of unencrypted password.
     * @param hashedValue String object of encrypted password.
     * @return boolean value. True if the unencrypted password equals to the encrypted one, false otherwise.
     */
    public static boolean check(String password, String hashedValue) {
        return BCrypt.checkpw(password, hashedValue);
    }
}
