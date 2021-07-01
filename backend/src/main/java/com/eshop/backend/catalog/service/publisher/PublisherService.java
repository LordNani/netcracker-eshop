package com.eshop.backend.catalog.service.publisher;

import com.eshop.backend.categories.dao.models.PublisherModel;

import java.util.List;

public interface PublisherService {

    List<PublisherModel> getAll();
    PublisherModel getById(Long id);
}
