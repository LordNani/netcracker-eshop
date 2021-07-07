package com.eshop.backend.service.implementations;

import com.eshop.backend.model.PublisherModel;
import com.eshop.backend.repository.interfaces.PublisherDao;
import com.eshop.backend.service.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    PublisherDao publisherDao;

    @Autowired
    public PublisherServiceImpl (PublisherDao publisherDao) {this.publisherDao = publisherDao;}

    @Override
    public List<PublisherModel> getAll() {
        return publisherDao.getAll();
    }

    @Override
    public PublisherModel getById(Long id) {
        return publisherDao.getById(id);
    }
}
