package com.eshop.backend.catalog.service.language;

import com.eshop.backend.categories.dao.models.LanguageModel;

import java.util.List;

public interface LanguageService {

    List<LanguageModel> getAll();
    LanguageModel getById(Long id);
}
