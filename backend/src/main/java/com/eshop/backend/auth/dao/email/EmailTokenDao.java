package com.eshop.backend.auth.dao.email;

import com.eshop.backend.utils.CrudDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;

public interface EmailTokenDao extends CrudDao<EmailTokenModel> {
    void createVerificationToken(AuthorizedUserModel user, EmailTokenModel token);
    EmailTokenModel getByToken(String token, String name);
    void deleteByValue(String token);
}
