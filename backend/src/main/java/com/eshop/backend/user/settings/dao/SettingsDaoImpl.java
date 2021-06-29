package com.eshop.backend.user.settings.dao;


import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class SettingsDaoImpl implements SettingsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SettingsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public AuthorizedUserModel getByLogin(String login) {
        try{
            String getUserSql = "SELECT id, userlogin, username, " +
                    "usersurname, useraddress, usernumber, userrole FROM authorizeduser WHERE userlogin = ?";

            RowMapper<AuthorizedUserModel> rowMapper = (rs, rowNum) -> AuthorizedUserModel.builder()
                    .id(rs.getLong("id"))
                    .userLogin(rs.getString("userlogin"))
                    .userName(rs.getString("username"))
                    .userSurname(rs.getString("usersurname"))
                    .userNumber(rs.getString("usernumber"))
                    .userAddress(rs.getString("useraddress"))
                    .userRole(rs.getString("userrole"))
                    .build();
            AuthorizedUserModel user = jdbcTemplate.queryForObject(getUserSql, rowMapper, login);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void create(AuthorizedUserModel model) {

    }

    @Override
    public AuthorizedUserModel getById(Long id) {
        return null;
    }

    @Override
    public List<AuthorizedUserModel> getAll() {
        return Collections.emptyList();
    }

    @Override
    public void update(AuthorizedUserModel model) {
        String SQL = "update authorizeduser set userlogin = ?, " +
                "username = ?, usersurname = ?, " +
                "useraddress = ?, usernumber = ? " +
                "where id = ?";


            jdbcTemplate.update(SQL, model.getUserLogin(), model.getUserName(), model.getUserSurname(),
                    model.getUserAddress(), model.getUserNumber(), model.getId());

    }

    @Override
    public void delete(Long id) {

    }
}
