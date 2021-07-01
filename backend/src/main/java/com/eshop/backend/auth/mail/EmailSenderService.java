package com.eshop.backend.auth.mail;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.UUID;

@Service
public class EmailSenderService {

    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailTokenDao emailTokenDao;
    private final JavaMailSender mailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public EmailSenderService(AuthorizedUserDao authorizedUsersDao, EmailTokenDao emailTokenDao, JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailTokenDao = emailTokenDao;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
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
        email.setText(message + "\r\n" + "http://localhost:4200" + confirmationUrl);
//        email.setText(html);
        mailSender.send(email);
    }
}
