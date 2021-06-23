package com.eshop.backend.user.settings.services;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.exceptions.ChangeExistMailException;
import com.eshop.backend.auth.exceptions.UserAlreadyExistsException;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.user.dao.settings.SettingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final  SettingsDao settingsDao;
    private final AuthorizedUserDao authorizedUserDao;
    private final EmailSenderService emailSenderService;

    @Autowired
    public SettingsServiceImpl(SettingsDao settingsDao, AuthorizedUserDao authorizedUserDao, EmailSenderService emailSenderService) {
        this.settingsDao = settingsDao;
        this.authorizedUserDao = authorizedUserDao;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) {
        return settingsDao.getByLogin(login);
    }

    @Override
    public void update(AuthorizedUserModel authorizedUserModel) {


        if (!authorizedUserModel.getUserLogin().equals(
                authorizedUserDao.getLoginById(authorizedUserModel.getId()))){
            //change status to anon
            AuthorizedUserModel temp = authorizedUserDao.getByLogin(authorizedUserModel.getUserLogin());
            if (authorizedUserDao.getByLogin(authorizedUserModel.getUserLogin()) != null) {
                throw new ChangeExistMailException();
            }
            authorizedUserModel.setUserStatus(Role.ANONYMOUS.name());
            authorizedUserDao.setStatus(authorizedUserModel);
            emailSenderService.sendEmail(authorizedUserModel, "emailVerify");
        }
        settingsDao.update(authorizedUserModel);
    }

}
