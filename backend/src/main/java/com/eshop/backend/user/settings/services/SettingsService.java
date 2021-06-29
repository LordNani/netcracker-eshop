package com.eshop.backend.user.settings.services;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;

public interface SettingsService {

    AuthorizedUserModel getByLogin(String login);
    void update(AuthorizedUserModel authorizedUserModel);

}
