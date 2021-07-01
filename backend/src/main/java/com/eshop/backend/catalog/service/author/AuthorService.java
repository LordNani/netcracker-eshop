package com.eshop.backend.catalog.service.author;

import com.eshop.backend.categories.dao.models.AuthorModel;

import java.util.List;

public interface AuthorService {

    List<AuthorModel> getAll();
    AuthorModel getById(Long id);
}
