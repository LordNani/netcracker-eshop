package com.eshop.backend.catalog.service.genre;

import com.eshop.backend.categories.dao.models.GenreModel;

import java.util.List;

public interface GenreService {

    List<GenreModel> getAll();
    GenreModel getById(Long id);
}
