package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.AuthorModel;

import java.util.List;

public interface AuthorService {

    List<AuthorModel> getAll();
    AuthorModel getById(Long id);
}
