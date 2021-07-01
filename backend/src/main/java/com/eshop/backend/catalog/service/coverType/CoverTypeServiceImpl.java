package com.eshop.backend.catalog.service.coverType;

import com.eshop.backend.categories.dao.coverType.CoverTypeDao;
import com.eshop.backend.categories.dao.models.CoverTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverTypeServiceImpl implements CoverTypeService {

    CoverTypeDao coverTypeDao;

    @Autowired
    public CoverTypeServiceImpl(CoverTypeDao coverTypeDao) {
        this.coverTypeDao = coverTypeDao;
    }

    @Override
    public List<CoverTypeModel> getAll() {
        return coverTypeDao.getAll();
    }

    @Override
    public CoverTypeModel getById(Long id) {
        return coverTypeDao.getById(id);
    }
}
