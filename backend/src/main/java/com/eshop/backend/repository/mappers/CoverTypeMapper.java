package com.eshop.backend.repository.mappers;

import com.eshop.backend.model.CoverTypeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoverTypeMapper implements RowMapper <CoverTypeModel>{

    public static final String SELECT_SQL = "select id, covertypename from covertype ";

    @Override
    public CoverTypeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("covertypename");
        return new CoverTypeModel(id, name);
    }
}
