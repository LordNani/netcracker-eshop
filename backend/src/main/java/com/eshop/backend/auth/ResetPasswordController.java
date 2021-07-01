package com.eshop.backend.auth;

import com.eshop.backend.auth.exceptions.NoUserWithThisEmailException;
import com.eshop.backend.auth.exceptions.NewPasswordSameAsOldException;
import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import com.eshop.backend.auth.dto.ForgotPasswordDTO;
import com.eshop.backend.auth.dto.ResetPasswordDTO;
import com.eshop.backend.auth.dto.ValidateResetTokenDTO;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/user")
public class ResetPasswordController {

    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailSenderService emailSenderService;
    private final EmailTokenDao emailTokenDao;
    private final PasswordValidator passwordValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ResetPasswordController(AuthorizedUserDao authorizedUsersDao, EmailSenderService emailSenderService, EmailTokenDao emailTokenDao, PasswordValidator passwordValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailSenderService = emailSenderService;
        this.emailTokenDao = emailTokenDao;
        this.passwordValidator = passwordValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {

        AuthorizedUserModel user = authorizedUsersDao.getByLogin(forgotPasswordDTO.getEmail());
        if (user == null) {
            throw new NoUserWithThisEmailException();
        }

        emailSenderService.sendEmail(user, "resetPassword");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestBody ValidateResetTokenDTO validateResetTokenDTO) {
        String confirmationToken = validateResetTokenDTO.getToken();

        if (confirmationToken != null) {
            EmailTokenModel emailTokenModel = emailTokenDao.getByToken(confirmationToken, "resetPassword");

            Calendar cal = Calendar.getInstance();

            if (emailTokenModel.getTokenExpiryDate().getTime() - cal.getTime().getTime() >= 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {

        if (passwordValidator.isValid(resetPasswordDTO.getPassword()) &&
            resetPasswordDTO.getPassword().equals(resetPasswordDTO.getConfirmPassword())) {

            AuthorizedUserModel user = authorizedUsersDao.getByToken(resetPasswordDTO.getToken());

            if (bCryptPasswordEncoder.matches(resetPasswordDTO.getPassword(), user.getUserPassword())) {
                throw new NewPasswordSameAsOldException();
            }

            user.setUserPassword(resetPasswordDTO.getPassword());

            authorizedUsersDao.update(user);

            emailTokenDao.deleteByValue(resetPasswordDTO.getToken());

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
