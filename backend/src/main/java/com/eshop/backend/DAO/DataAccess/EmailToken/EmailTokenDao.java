package com.eshop.backend.DAO.DataAccess.EmailToken;

import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import com.eshop.backend.DAO.Models.EmailToken;

public interface EmailTokenDao extends MainDao<EmailToken> {
    void createVerificationToken(AuthorizedUser user, EmailToken token);
    EmailToken getByToken(String token);
}
