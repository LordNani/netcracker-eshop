package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.LanguageModel;

import java.util.List;

public interface LanguageService {

    List<LanguageModel> getAll();
    LanguageModel getById(Long id);
}
