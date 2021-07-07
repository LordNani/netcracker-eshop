package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.LanguageModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageMapper implements RowMapper<LanguageModel> {

    public static final String SELECT_SQL = "select id, languagename from language ";

    @Override
    public LanguageModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("languagename");
        return new LanguageModel(id, name);
    }
}
