package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.AuthorModel;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<AuthorModel> {
    public static String SELECT_SQL = "select id, authorname from author ";

    @Override
    public AuthorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("authorname");
        return new AuthorModel(id, name);
    }
}
