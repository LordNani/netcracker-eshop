package com.eshop.backend.auth;

import com.eshop.backend.auth.exceptions.UserAlreadyExistsException;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

        AuthorizedUserModel user = authorizedUsersDao.getByLogin(request.getUserLogin());

        if (user != null) {
            throw new UserAlreadyExistsException();
        }

        if (emailValidator.isValid(request.getUserLogin()) &&
                passwordValidator.isValid(request.getUserPassword())) {

            user = AuthorizedUserModel.builder()
                    .userLogin(request.getUserLogin())
                    .userPassword(request.getUserPassword())
                    .userRole(Role.USER.name())
                    .userName(request.getFirstName())
                    .userSurname(request.getLastName())
                    .userRegistrationDate(new Date(System.currentTimeMillis()))
                    .userStatus(Role.ANONYMOUS.name())
                    .build();

            authorizedUsersDao.create(user);

            emailSenderService.sendEmail(user, "emailVerify");

        } else new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {

        if (confirmationToken != null) {
            EmailTokenModel emailTokenModel = emailTokenDao.getByToken(confirmationToken, "emailVerify");
            AuthorizedUserModel user = authorizedUsersDao.getById(emailTokenModel.getAuthorizedUserId());

            Calendar cal = Calendar.getInstance();

            if ((emailTokenModel.getTokenExpiryDate().getTime() - cal.getTime().getTime()) >= 0 &&
                    user != null) {
                user.setUserStatus(Role.AUTHORIZED.name());
                authorizedUsersDao.update(user);

                emailTokenDao.deleteByValue(confirmationToken);

                return new ResponseEntity<>(HttpStatus.OK);
            }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}