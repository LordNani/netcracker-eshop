package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.GenreModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<GenreModel> {

    public static final String SELECT_SQL = "select id, genrename from genre ";

    @Override
    public GenreModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("genrename");
        return new GenreModel(id, name);
    }
}
