package uz.uzkassa.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


/**
 * @author Dilshodbek Akhmedov, Fri 7:13 AM. 2/24/23
 */

@Slf4j
@Component
public class EmailUtils {
    @Value("${email.subject}")
    private String subject;
    @Value("${email.sender}")
    private String sender;
    @Value("${email.activation.text-prefix}")
    private String activationText;

    private final JavaMailSender emailSender;

    public EmailUtils(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendAsyncMessage(String email, String token) {
        CompletableFuture.runAsync(() -> {
            String text = String.format(activationText, token);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            log.info("Message send this email: " + email);
        });
    }
}
