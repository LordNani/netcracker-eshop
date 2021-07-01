package com.eshop.backend.catalog.service.coverType;

import com.eshop.backend.categories.dao.models.CoverTypeModel;

import java.util.List;

public interface CoverTypeService {
    List<CoverTypeModel> getAll();
    CoverTypeModel getById(Long id);
}
