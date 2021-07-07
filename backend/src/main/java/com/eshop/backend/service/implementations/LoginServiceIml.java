package com.eshop.backend.service.implementations;

import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.repository.implementations.AuthorizedUserDaoImpl;
import com.eshop.backend.service.interfaces.LoginService;
import com.eshop.backend.dto.LoginRequestDTO;
import com.eshop.backend.exceptions.NeedMailConfirmationException;
import com.eshop.backend.exceptions.NoUserWithThisEmailException;
import com.eshop.backend.exceptions.WrongEmailOrPasswordException;
import com.eshop.backend.utils.Role;
import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceIml implements LoginService {

    private final AuthorizedUserDao authorizedUserDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CaptchaService captchaService;

    @Autowired
    public LoginServiceIml(AuthorizedUserDaoImpl authorizedUserDao, BCryptPasswordEncoder bCryptPasswordEncoder, CaptchaService captchaService) {
        this.authorizedUserDao = authorizedUserDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.captchaService = captchaService;
    }

    @Override
    public AuthorizedUserModel login(LoginRequestDTO request) {
        boolean captchaVerified = captchaService.verify(request.getRecaptchaResponse());

        AuthorizedUserModel user = authorizedUserDao.getByLogin(request.getUserLogin());

        if (user == null) throw new NoUserWithThisEmailException();

        if (!user.getUserStatus().equals(Role.ACTIVE.name())) {
            throw new NeedMailConfirmationException();
        }
        if (captchaVerified && bCryptPasswordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            return user;
        } else {
            throw new WrongEmailOrPasswordException();
        }
    }

    @Override
    public AuthorizedUserModel getUserRole(String login) {
        return authorizedUserDao.getRoleByLogin(login);
    }

}
