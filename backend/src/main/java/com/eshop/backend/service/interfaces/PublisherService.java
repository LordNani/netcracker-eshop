package com.eshop.backend.service.interfaces;

import com.eshop.backend.model.PublisherModel;

import java.util.List;

public interface PublisherService {

    List<PublisherModel> getAll();
    PublisherModel getById(Long id);
}
