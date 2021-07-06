package com.eshop.backend.auth.services;

import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
import com.eshop.backend.auth.exceptions.UserAlreadyExistsException;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailValidator emailValidator;
    private final AuthorizedUserDao authorizedUserDao;
    private final EmailTokenDao emailTokenDao;
    private final PasswordValidator passwordValidator;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationServiceImpl(EmailValidator emailValidator, AuthorizedUserDao authorizedUserDao,
                                   EmailTokenDao emailTokenDao, PasswordValidator passwordValidator,
                                   EmailSenderService emailSenderService) {
        this.emailValidator = emailValidator;
        this.authorizedUserDao = authorizedUserDao;
        this.emailTokenDao = emailTokenDao;
        this.passwordValidator = passwordValidator;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public boolean registration(RegistationRequestDTO request) {
        AuthorizedUserModel user = authorizedUserDao.getByLogin(request.getUserLogin());

        if (user != null) {
            throw new UserAlreadyExistsException();
        }

        if (emailValidator.isValid(request.getUserLogin()) &&
                passwordValidator.isValid(request.getUserPassword())) {

            user = AuthorizedUserModel.builder()
                    .userLogin(request.getUserLogin())
                    .userPassword(request.getUserPassword())
                    .userRole(Role.USER.name())
                    .userName(request.getUserName())
                    .userSurname(request.getUserSurname())
                    .userRegistrationDate(new Date(System.currentTimeMillis()))
                    .userStatus(Role.INACTIVE.name())
                    .userAddress("no address")
                    .userNumber("no number")
                    .build();

            authorizedUserDao.create(user);

            emailSenderService.sendEmail(authorizedUserDao.getByLogin(user.getUserLogin()), "emailVerify");

        } else return false;
        return true;
    }

    @Override
    public boolean confirmUserAccount(String confirmationToken) {
        if (confirmationToken != null) {
            EmailTokenModel emailTokenModel = emailTokenDao.getByToken(confirmationToken, "emailVerify");
            AuthorizedUserModel user = authorizedUserDao.getById(emailTokenModel.getAuthorizedUserId());

            if (user != null &&
                    emailTokenModel.getTokenExpiryDate().isAfter(LocalDateTime.now())) {
                user.setUserStatus(Role.ACTIVE.name());
                authorizedUserDao.setStatus(user);

                emailTokenDao.deleteByValue(confirmationToken);

                return true;
            }
        }
        return false;
    }
}
