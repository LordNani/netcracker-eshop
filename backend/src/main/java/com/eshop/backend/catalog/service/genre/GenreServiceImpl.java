package com.eshop.backend.catalog.service.genre;

import com.eshop.backend.categories.dao.genre.GenreDao;
import com.eshop.backend.categories.dao.models.GenreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    GenreDao genreDao;

    @Autowired
    public GenreServiceImpl (GenreDao genreDao) {this.genreDao = genreDao;}

    @Override
    public List<GenreModel> getAll() {
        return genreDao.getAll();
    }

    @Override
    public GenreModel getById(Long id) {
        return genreDao.getById(id);
    }
}
