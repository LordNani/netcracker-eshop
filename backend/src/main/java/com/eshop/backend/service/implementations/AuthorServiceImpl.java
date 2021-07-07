package com.eshop.backend.service.implementations;

import com.eshop.backend.repository.interfaces.AuthorDao;
import com.eshop.backend.model.AuthorModel;
import com.eshop.backend.service.interfaces.AuthorService;
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
