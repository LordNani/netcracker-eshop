package com.eshop.backend.controller;

import com.eshop.backend.service.interfaces.PublisherService;
import com.eshop.backend.model.PublisherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherCrudController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherCrudController (PublisherService publisherService) {this.publisherService = publisherService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PublisherModel> getById(@PathVariable("id")Long id) {
        PublisherModel publisher = publisherService.getById(id);
        return new ResponseEntity(publisher, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<PublisherModel> getAllPublishers() {
        List<PublisherModel> publishers = publisherService.getAll();
        return new ResponseEntity(publishers, HttpStatus.OK);
    }

}
