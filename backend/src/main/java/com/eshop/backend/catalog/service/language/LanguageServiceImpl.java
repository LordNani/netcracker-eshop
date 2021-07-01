package com.eshop.backend.catalog.service.language;

import com.eshop.backend.categories.dao.language.LanguageDao;
import com.eshop.backend.categories.dao.models.LanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService{

    LanguageDao languageDao;

    @Autowired
    public LanguageServiceImpl (LanguageDao languageDao) {this.languageDao = languageDao;}

    @Override
    public List<LanguageModel> getAll() {
        return languageDao.getAll();
    }

    @Override
    public LanguageModel getById(Long id) {
        return languageDao.getById(id);
    }
}
