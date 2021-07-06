package com.eshop.backend.auth.services;

import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dto.ForgotPasswordDTO;
import com.eshop.backend.auth.dto.ResetPasswordDTO;
import com.eshop.backend.auth.dto.ValidateResetTokenDTO;
import com.eshop.backend.auth.exceptions.NewPasswordSameAsOldException;
import com.eshop.backend.auth.exceptions.NoUserWithThisEmailException;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.PasswordValidator;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailSenderService emailSenderService;
    private final EmailTokenDao emailTokenDao;
    private final PasswordValidator passwordValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ResetPasswordServiceImpl(AuthorizedUserDao authorizedUsersDao, EmailSenderService emailSenderService, EmailTokenDao emailTokenDao, PasswordValidator passwordValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailSenderService = emailSenderService;
        this.emailTokenDao = emailTokenDao;
        this.passwordValidator = passwordValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        AuthorizedUserModel user = authorizedUsersDao.getByLogin(forgotPasswordDTO.getEmail());
        if (user == null)
            throw new NoUserWithThisEmailException();
        emailSenderService.sendEmail(user, "resetPassword");
    }

    @Override
    public boolean validateResetToken(ValidateResetTokenDTO validateResetTokenDTO) {
        String confirmationToken = validateResetTokenDTO.getToken();

        if (confirmationToken != null) {
            EmailTokenModel emailTokenModel = emailTokenDao.getByToken(confirmationToken, "resetPassword");

            return emailTokenModel.getTokenExpiryDate().isAfter(LocalDateTime.now());
        }

        return false;
    }

    @Override
    public boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (passwordValidator.isValid(resetPasswordDTO.getPassword()) &&
                resetPasswordDTO.getPassword().equals(resetPasswordDTO.getConfirmPassword())) {

            AuthorizedUserModel user = authorizedUsersDao.getByToken(resetPasswordDTO.getToken());

            if (bCryptPasswordEncoder.matches(resetPasswordDTO.getPassword(), user.getUserPassword())) {
                throw new NewPasswordSameAsOldException();
            }

            user.setUserPassword(resetPasswordDTO.getPassword());

            authorizedUsersDao.update(user);

            emailTokenDao.deleteByValue(resetPasswordDTO.getToken());

            return true;
        }

        return false;
    }
}
