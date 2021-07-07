package com.eshop.backend.service.implementations;

import com.eshop.backend.repository.interfaces.CoverTypeDao;
import com.eshop.backend.model.CoverTypeModel;
import com.eshop.backend.service.interfaces.CoverTypeService;
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
