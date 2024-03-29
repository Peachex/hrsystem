package com.epam.hrsystem.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Authenticator;
import java.util.Properties;

/**
 * Class provides session for email sending.
 *
 * @author Aleksey Klevitov
 */
public class SessionFactory {
    private static final String MAIL_USER_FIELD = "mail.user.name";
    private static final String MAIL_USER_PASSWORD_FIELD = "mail.user.password";

    /**
     * Creates Session object.
     *
     * @param configProperties Properties object that contains properties for email sending.
     * @return Session object.
     */
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty(MAIL_USER_FIELD);
        String userPassword = configProperties.getProperty(MAIL_USER_PASSWORD_FIELD);
        return Session.getDefaultInstance(configProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
