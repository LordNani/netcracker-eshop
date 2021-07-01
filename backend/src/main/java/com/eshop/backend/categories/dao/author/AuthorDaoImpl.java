package com.eshop.backend.categories.dao.author;

import com.eshop.backend.categories.dao.models.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao{

    @Autowired
    JdbcTemplate template;


    @Override
    public void create(AuthorModel model) {

    }

    @Override
    public AuthorModel getById(Long id) {
        String sql = AuthorMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new AuthorMapper(), new Object[]{Long.valueOf(id)});

    }

    @Override
    public List<AuthorModel> getAll() {
        String sql = AuthorMapper.SELECT_SQL;
        return template.query(sql, new AuthorMapper());
    }

    @Override
    public void update(AuthorModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
