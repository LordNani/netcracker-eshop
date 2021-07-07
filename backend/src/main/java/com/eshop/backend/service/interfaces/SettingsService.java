package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.AuthorizedUserModel;

public interface SettingsService {

    AuthorizedUserModel getByLogin(String login);
    void update(AuthorizedUserModel authorizedUserModel);

}
