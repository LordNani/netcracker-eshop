package com.eshop.backend.repository.interfaces;

import com.eshop.backend.repository.generic.CrudDao;
import com.eshop.backend.model.AuthorizedUserModel;
import com.eshop.backend.model.EmailTokenModel;

public interface EmailTokenDao extends CrudDao<EmailTokenModel> {
    void createVerificationToken(AuthorizedUserModel user, EmailTokenModel token);

    EmailTokenModel getByToken(String token, String name);

    void deleteByValue(String token);
}
