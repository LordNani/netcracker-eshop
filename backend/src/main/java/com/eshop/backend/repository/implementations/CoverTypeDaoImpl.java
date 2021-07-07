package com.eshop.backend.repository.implementations;

import com.eshop.backend.repository.mappers.CoverTypeMapper;
import com.eshop.backend.model.CoverTypeModel;
import com.eshop.backend.repository.interfaces.CoverTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoverTypeDaoImpl implements CoverTypeDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(CoverTypeModel model) {

    }

    @Override
    public CoverTypeModel getById(Long id) {
        String sql = CoverTypeMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new CoverTypeMapper(), new Object[]{Long.valueOf(id)});

    }

    @Override
    public List<CoverTypeModel> getAll() {
        String sql = CoverTypeMapper.SELECT_SQL;
        return template.query(sql, new CoverTypeMapper());
    }

    @Override
    public void update(CoverTypeModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
