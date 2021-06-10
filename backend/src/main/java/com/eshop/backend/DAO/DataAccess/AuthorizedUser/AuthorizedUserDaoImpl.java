package com.eshop.backend.dao.DataAccess.AuthorizedUser;

import com.eshop.backend.dao.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorizedUserDaoImpl implements AuthorizedUserDao {

    private JdbcTemplate jdbcTemplate;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthorizedUserDaoImpl(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void create(AuthorizedUser user) {
        String SQL = "insert into users (user_login, user_password," +
                "user_role, user_status)\n" +
                "values (?,?,?,?)";
        try {
            jdbcTemplate.update(SQL, user.getEmail(), user.getPassword(),
                    user.getRole(), user.getStatus());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public AuthorizedUser getByLogin(String login) throws DataAccessException {
        try{
            String getUserSql = "SELECT id, user_login , user_password, user_role, user_status FROM users WHERE user_login = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public AuthorizedUser getByStatus(String status) {
        return null;
    }

    @Override
    public AuthorizedUser getRoleByLogin(String login) {
        try{
            String getUserSql = "SELECT id, user_login , user_password, user_role, user_status FROM users WHERE user_login = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), login);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public AuthorizedUser getByToken(String token) {
        try{
            String getUserSql = "SELECT users.id, user_login , user_password, user_role, user_status from users\n" +
                    "left join email_token et on users.id = et.user_id\n" +
                    "WHERE et.token = ?";
            return jdbcTemplate.queryForObject(getUserSql, new CustomerRowMapper(), token);
        } catch (DataAccessException e) {
            String str = e.toString();
            return null;
        }
    }

    @Override
    public void createVerificationToken(AuthorizedUser user, String token) {
        //save token
        String SQL = "update users set \"user_login\" = ?,\n" +
                "                   \"user_password\"= ?,\n" +
                "                   \"user_role\" = ?,\n" +
                "                   \"user_status\" = ?,\n" +
                "                   \"user_token\" = ?\n" +
                "                   where id = ?";
        try {
            jdbcTemplate.update(SQL, user.getEmail(), user.getPassword(), user.getRole(),
                    user.getStatus(), token, user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }


    @Override
    public AuthorizedUser getById(int id) {
        return null;
    }

    @Override
    public List<AuthorizedUser> getAll() {
        return null;
    }

    @Override
    public void update(AuthorizedUser user) {
        String SQL = "update users set \"user_status\" = ?\n" +
                "                   where id = ?";
        try {
            jdbcTemplate.update(SQL, user.getStatus(), user.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public void delete(Long id) {

    }
}
