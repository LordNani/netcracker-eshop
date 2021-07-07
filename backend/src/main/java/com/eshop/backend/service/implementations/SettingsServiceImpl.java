package com.eshop.backend.service.implementations;

import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.exceptions.ChangeExistMailException;
import com.eshop.backend.service.implementations.EmailSenderService;
import com.eshop.backend.service.interfaces.SettingsService;
import com.eshop.backend.utils.Role;
import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final AuthorizedUserDao authorizedUserDao;
    private final EmailSenderService emailSenderService;

    @Autowired
    public SettingsServiceImpl(AuthorizedUserDao authorizedUserDao, EmailSenderService emailSenderService) {
        this.authorizedUserDao = authorizedUserDao;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public AuthorizedUserModel getByLogin(String login) {
        return authorizedUserDao.getByLoginInfo(login);
    }

    @Override
    public void update(AuthorizedUserModel authorizedUserModel) {
        if (!authorizedUserModel.getUserLogin().equals(
                authorizedUserDao.getLoginById(authorizedUserModel.getId()))) {
            //change status to anon
            if (authorizedUserDao.getByLogin(authorizedUserModel.getUserLogin()) != null) {
                throw new ChangeExistMailException();
            }
            authorizedUserModel.setUserStatus(Role.INACTIVE.name());
            authorizedUserDao.setStatus(authorizedUserModel);
            emailSenderService.sendEmail(authorizedUserModel, "emailVerify");
        }
        authorizedUserDao.updateInfo(authorizedUserModel);
    }

}
