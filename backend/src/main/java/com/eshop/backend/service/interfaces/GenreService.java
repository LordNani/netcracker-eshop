package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.GenreModel;

import java.util.List;

public interface GenreService {

    List<GenreModel> getAll();
    GenreModel getById(Long id);
}
