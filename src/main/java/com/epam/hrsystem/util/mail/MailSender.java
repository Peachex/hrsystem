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
    private MailSender() {
    }

    public String getSendToEmail() {
        return sendToEmail;
    }

    public void setSendToEmail(String sendToEmail) {
        this.sendToEmail = sendToEmail;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public void setupEmail(String sendToEmail, String mailSubject, String mailText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
    }

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

    public enum MailSenderHolder {
        HOLDER;
        private final MailSender mailSender = new MailSender();

        public MailSender getMailSender() {
            return mailSender;
        }
    }
}
