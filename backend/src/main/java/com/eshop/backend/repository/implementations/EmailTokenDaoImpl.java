package com.eshop.backend.repository.implementations;

import com.eshop.backend.repository.interfaces.EmailTokenDao;
import com.eshop.backend.model.AuthorizedUserModel;
import com.eshop.backend.model.EmailTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
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
        String SQL = "INSERT INTO verificationtoken (tokenname, tokenvalue," +
                "tokenexpirydate, authorizeduserid) VALUES (?,?,?,?)";

        jdbcTemplate.update(SQL, token.getTokenName(), token.getTokenValue(),
                token.getTokenExpiryDate(), token.getAuthorizedUserId());
    }

    @Override
    public EmailTokenModel getByToken(String token, String name) {
        try {
            String getByTokenSql = "SELECT id, tokenname , tokenvalue," +
                    " tokenexpirydate, authorizeduserid " +
                    "FROM verificationtoken WHERE tokenvalue = ?" +
                    " AND tokenname = ?";

            return jdbcTemplate.queryForObject(getByTokenSql, (rs, rowNum) ->
                    new EmailTokenModel(
                            rs.getLong("id"),
                            rs.getString("tokenname"),
                            rs.getString("tokenvalue"),
                            rs.getTimestamp("tokenexpirydate").toLocalDateTime(),
                            rs.getLong("authorizeduserid")
                    ), token, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteByValue(String token) {
        String SQL = "DELETE FROM verificationtoken WHERE tokenvalue = ?";
        jdbcTemplate.update(SQL, token);
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
        return new ArrayList<>();
    }

    @Override
    public void update(EmailTokenModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
