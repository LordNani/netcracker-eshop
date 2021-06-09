package com.eshop.backend.auth.controllers;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.DataAccess.EmailToken.EmailTokenDao;
import com.eshop.backend.DAO.Models.EmailToken;
import com.eshop.backend.DAO.Models.Role;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@RestController
public class RegistrationController {

    private final EmailValidator emailValidator;
    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailTokenDao emailTokenDao;
    private final PasswordValidator passwordValidator;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationController(EmailValidator emailValidator, AuthorizedUserDao authorizedUsersDao,
                                  EmailTokenDao emailTokenDao, PasswordValidator passwordValidator, EmailSenderService emailSenderService) {
        this.emailValidator = emailValidator;
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailTokenDao = emailTokenDao;
        this.passwordValidator = passwordValidator;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> Registration(@RequestBody RegistationRequestDTO request) {

        AuthorizedUser user = authorizedUsersDao.getByLogin(request.getUserLogin());

        if (user != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (emailValidator.isValid(request.getUserLogin()) &&
                passwordValidator.isValid(request.getUserPassword())) {

            user = AuthorizedUser.builder()
                    .userLogin(request.getUserLogin())
                    .userPassword(request.getUserPassword())
                    .userRole(Role.USER.name())
                    .userName(request.getFirstName())
                    .userRegistrationDate(new Date(System.currentTimeMillis()))
                    .userStatus(Role.ANONYMOUS.name())
                    .build();

            authorizedUsersDao.create(user);

            emailSenderService.sendEmail(user);

        } else new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {

        if (confirmationToken != null) {
            EmailToken emailToken = emailTokenDao.getByToken(confirmationToken);
            AuthorizedUser user = authorizedUsersDao.getByToken(confirmationToken);

            Calendar cal = Calendar.getInstance();

            if ((emailToken.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
                user.setUserStatus(Role.AUTHORIZED.name());
                authorizedUsersDao.update(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
