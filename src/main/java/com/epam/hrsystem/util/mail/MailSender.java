package com.epam.hrsystem.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * Class provides methods for email sending.
 *
 * @author Aleksey Klevitov
 */
public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_PROPERTY_FILE_PATH = "/property/mail.properties";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(MailSender.class.getResourceAsStream(MAIL_PROPERTY_FILE_PATH));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;

    /**
     * Enumeration with a single object in it (thread-safe singleton) used to MailSender object manage.
     */
    public enum MailSenderHolder {
        /**
         * Represents a singleton pattern realization.
         */
        HOLDER;
        private final MailSender mailSender = new MailSender();

        /**
         * Getter method that returns a mail sender object.
         *
         * @return MailSender object.
         */
        public MailSender getMailSender() {
            return mailSender;
        }
    }

    private MailSender() {
    }

    /**
     * Getter method that returns recipient's email.
     *
     * @return String object of recipient's email.
     */
    public String getSendToEmail() {
        return sendToEmail;
    }

    /**
     * Setter method of recipient's email.
     *
     * @param sendToEmail String object of recipient's email.
     */
    public void setSendToEmail(String sendToEmail) {
        this.sendToEmail = sendToEmail;
    }

    /**
     * Getter method that returns mail's subject.
     *
     * @return String object of mail's subject.
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * Setter method of mail's subject.
     *
     * @param mailSubject String object of mail's subject.
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * Getter method that returns mail's text.
     *
     * @return String object of mail's text.
     */
    public String getMailText() {
        return mailText;
    }

    /**
     * Setter method of mail's text.
     *
     * @param mailText String object of mail's text.
     */
    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    /**
     * Method that setups mail before sending.
     *
     * @param sendToEmail String object of recipient's email.
     * @param mailSubject String object of mail's subject.
     * @param mailText    String object of mail's text.
     */
    public void setupEmail(String sendToEmail, String mailSubject, String mailText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
    }

    /**
     * Method for sending an email to the recipient.
     */
    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            logger.log(Level.ERROR, "Invalid address: " + sendToEmail + "  " + e);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error generating or sending message: " + e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, CONTENT_TYPE);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
