package com.eshop.backend.dao.DataAccess.EmailToken;

import com.eshop.backend.dao.DataAccess.MainDao;
import com.eshop.backend.dao.Models.AuthorizedUser;
import com.eshop.backend.dao.Models.EmailToken;

public interface EmailTokenDao extends MainDao<EmailToken> {
    void createVerificationToken(AuthorizedUser user, EmailToken token);
    EmailToken getByToken(String token);
}
