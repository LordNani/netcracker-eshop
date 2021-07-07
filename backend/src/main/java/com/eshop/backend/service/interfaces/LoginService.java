package com.eshop.backend.service.interfaces;

import com.eshop.backend.dto.LoginRequestDTO;
import com.eshop.backend.model.AuthorizedUserModel;

public interface LoginService {
    AuthorizedUserModel login(LoginRequestDTO request);

    AuthorizedUserModel getUserRole(String login);
}
