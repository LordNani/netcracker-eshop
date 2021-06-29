package com.eshop.backend.auth.dao.email;


import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailTokenDaoImpl implements EmailTokenDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailTokenDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createVerificationToken(AuthorizedUserModel user, EmailTokenModel token) {
        String SQL = "insert into verificationtoken (tokenname, tokenvalue," +
                "tokenexpirydate, authorizeduserid)\n" +
                "values (?,?,?,?)";
        try {
            jdbcTemplate.update(SQL, token.getTokenName(), token.getTokenValue(),
                    token.getTokenExpiryDate(), token.getAuthorizedUserId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public EmailTokenModel getByToken(String token, String name) {
        try{
            String getByTokenSql = "SELECT id, tokenname , tokenvalue, tokenexpirydate, authorizeduserid " +
                    "FROM verificationtoken WHERE tokenvalue = ?" +
                    " AND tokenname = ?";
            return jdbcTemplate.queryForObject(getByTokenSql, (rs, rowNum) ->
                    new EmailTokenModel(
                            rs.getLong("id"),
                            rs.getString("tokenname"),
                            rs.getString("tokenvalue"),
                            rs.getDate("tokenexpirydate"),
                            rs.getLong("authorizeduserid")
                    ), token, name);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void deleteByValue(String token) {
        String SQL = "delete from verificationtoken where tokenvalue = ?";
        try {
            jdbcTemplate.update(SQL, token);
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public void create(EmailTokenModel model) {

    }

    @Override
    public EmailTokenModel getById(Long id) {
        return null;
    }

    @Override
    public List<EmailTokenModel> getAll() {
        return null;
    }

    @Override
    public void update(EmailTokenModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
