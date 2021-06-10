package com.eshop.backend.auth.controllers;

import com.eshop.backend.dao.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.dao.DataAccess.EmailToken.EmailTokenDao;
import com.eshop.backend.dao.Models.EmailToken;
import com.eshop.backend.dao.Models.Role;
import com.eshop.backend.auth.dto.RegistationRequestDTO;
import com.eshop.backend.dao.Models.AuthorizedUser;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.validator.EmailValidator;
import com.eshop.backend.auth.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class RegistrationController {

    private final EmailValidator emailValidator;
    private final AuthorizedUserDao authorizedUsersdao;
    private final EmailTokenDao emailTokendao;
    private final PasswordValidator passwordValidator;
    private final EmailSenderService emailSenderService;

    @Autowired
    public RegistrationController(EmailValidator emailValidator, AuthorizedUserDao authorizedUsersdao,
                                  EmailTokenDao emailTokendao, PasswordValidator passwordValidator, EmailSenderService emailSenderService) {
        this.emailValidator = emailValidator;
        this.authorizedUsersdao = authorizedUsersdao;
        this.emailTokendao = emailTokendao;
        this.passwordValidator = passwordValidator;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> Registration(@RequestBody RegistationRequestDTO request) {

        AuthorizedUser user = authorizedUsersdao.getByLogin(request.getEmail());

        if (user != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (emailValidator.isValid(request.getEmail()) &&
                passwordValidator.isValid(request.getPassword())) {

            user = new AuthorizedUser(request.getEmail(), request.getPassword(), Role.USER.name(), Role.ANONYMOUS.name());
            authorizedUsersdao.create(user);
            user = authorizedUsersdao.getByLogin(user.getEmail());

            emailSenderService.sendEmail(user);

        } else new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {

        if (confirmationToken != null) {
            EmailToken emailToken = emailTokendao.getByToken(confirmationToken);
            AuthorizedUser user = authorizedUsersdao.getByToken(confirmationToken);
//            user.setStatus(Role.AUTHORIZED.name());
//            authorizedUsersdao.update(user);

            Calendar cal = Calendar.getInstance();

            if ((emailToken.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
                user.setStatus(Role.AUTHORIZED.name());
                authorizedUsersdao.update(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
//            String token = JwtCreator.createJwt(user.getEmail());
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HEADER_STRING, TOKEN_PREFIX + token);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
