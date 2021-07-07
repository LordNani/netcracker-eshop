package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.CoverTypeModel;

import java.util.List;

public interface CoverTypeService {
    List<CoverTypeModel> getAll();
    CoverTypeModel getById(Long id);
}
