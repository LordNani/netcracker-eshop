package com.eshop.backend.catalog.service;

import com.eshop.backend.catalog.service.coverType.CoverTypeService;
import com.eshop.backend.categories.dao.models.CoverTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cover-type")
public class CoverTypeCrudController {
    private final CoverTypeService coverTypeService;

    @Autowired
    public CoverTypeCrudController (CoverTypeService coverTypeService) {this.coverTypeService = coverTypeService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CoverTypeModel> getById(@PathVariable("id")Long id) {
        CoverTypeModel coverType = coverTypeService.getById(id);
        return new ResponseEntity(coverType, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<CoverTypeModel> getAllCoverTypes() {
        List<CoverTypeModel> coverTypes = coverTypeService.getAll();
        return new ResponseEntity(coverTypes, HttpStatus.OK);
    }
}
