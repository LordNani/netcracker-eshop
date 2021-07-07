package com.eshop.backend.service.implementations;

import com.eshop.backend.repository.interfaces.EmailTokenDao;
import com.eshop.backend.model.AuthorizedUserModel;
import com.eshop.backend.model.EmailTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailSenderService {

    @Value("${frontend.base.url}")
    private String FRONT_BASE_URL;

    private final EmailTokenDao emailTokenDao;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(EmailTokenDao emailTokenDao, JavaMailSender mailSender) {
        this.emailTokenDao = emailTokenDao;
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(AuthorizedUserModel user, String type) {
        String token = UUID.randomUUID().toString();

        EmailTokenModel emailTokenModel = new EmailTokenModel(type, token, user.getId());
        emailTokenDao.createVerificationToken(user, emailTokenModel);

        String recipientAddress = user.getUserLogin();

        String subject = "";
        String confirmationUrl = "";
        String message = "";

        if (type.equals("emailVerify")) {
            subject = "Registration Confirmation";
            confirmationUrl = "/verify-email?token=" + token;
            message = "To confirm your e-mail address, please click the link below:\n";
        }
        if (type.equals("resetPassword")) {
            subject = "Reset password Confirmation";
            confirmationUrl = "/reset-password?token=" + token;
            message = "Please click the below link to reset your password, the link will be valid for 1 day:\n";
        }

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + FRONT_BASE_URL + confirmationUrl);
        mailSender.send(email);
    }
}
