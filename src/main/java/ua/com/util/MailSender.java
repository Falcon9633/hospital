package ua.com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
    private static final Logger LOGGER = LogManager.getLogger(MailSender.class);

    public static void send(String email, String login, String password) throws SQLException {
        LOGGER.debug("send starts");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hospitalregemail@gmail.com", "Hospital");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hospitalregemail@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("You have been registered in Hospital!");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(String.format("Your login -> %s\nYour password -> %s", login, password));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new SQLException("Exception during sending email", e.getCause());
        }
    }
}
