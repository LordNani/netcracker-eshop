package com.eshop.backend.catalog.service.author;

import com.eshop.backend.categories.dao.author.AuthorDao;
import com.eshop.backend.categories.dao.models.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl (AuthorDao authorDao) {this.authorDao = authorDao;}

    @Override
    public List<AuthorModel> getAll() {
        return authorDao.getAll();
    }

    @Override
    public AuthorModel getById(Long id) {
        return authorDao.getById(id);
    }
}
