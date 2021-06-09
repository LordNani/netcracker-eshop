package com.eshop.backend.auth.mail;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.DataAccess.EmailToken.EmailTokenDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.DAO.Models.EmailToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmailSenderService {

    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailTokenDao emailTokenDao;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(AuthorizedUserDao authorizedUsersDao, EmailTokenDao emailTokenDao, JavaMailSender mailSender) {
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailTokenDao = emailTokenDao;
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(AuthorizedUser user) {
        String token = UUID.randomUUID().toString();

        EmailToken emailToken = new EmailToken(token, user.getId());
        emailTokenDao.createVerificationToken(user, emailToken);

        String recipientAddress = user.getUserLogin();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/user/confirm-account?token=" + token;
        String message = "To confirm your e-mail address, please click the link below:\n";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8081" + confirmationUrl);
        mailSender.send(email);
    }
}
