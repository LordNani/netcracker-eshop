package com.eshop.backend.repository.implementations;

import com.eshop.backend.model.PublisherModel;
import com.eshop.backend.repository.mappers.PublisherMapper;
import com.eshop.backend.repository.interfaces.PublisherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDaoImpl implements PublisherDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(PublisherModel model) {

    }

    @Override
    public PublisherModel getById(Long id) {
        String sql = PublisherMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new PublisherMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List<PublisherModel> getAll() {
        String sql = PublisherMapper.SELECT_SQL;
        return template.query(sql, new PublisherMapper());
    }

    @Override
    public void update(PublisherModel model) {

    }

    @Override
    public void delete(Long id) {

    }
}
