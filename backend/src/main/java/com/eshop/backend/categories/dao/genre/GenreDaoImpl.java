package com.eshop.backend.categories.dao.genre;

import com.eshop.backend.categories.dao.models.GenreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao{

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(GenreModel model) {

    }

    @Override
    public GenreModel getById(Long id) {
        String sql = GenreMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new GenreMapper(), new Object[]{Long.valueOf(id)});

    }

    @Override
    public List<GenreModel> getAll() {
        String sql = GenreMapper.SELECT_SQL;
        return template.query(sql, new GenreMapper());
    }

    @Override
    public void update(GenreModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
