package com.eshop.backend.catalog.service;

import com.eshop.backend.catalog.service.language.LanguageService;
import com.eshop.backend.categories.dao.models.LanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageCrudController {
    private final LanguageService languageService;

    @Autowired
    public LanguageCrudController (LanguageService languageService) {this.languageService = languageService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<LanguageModel> getById(@PathVariable("id")Long id) {
        LanguageModel language = languageService.getById(id);
        return new ResponseEntity(language, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<LanguageModel> getAllLanguages() {
        List<LanguageModel> languages = languageService.getAll();
        return new ResponseEntity(languages, HttpStatus.OK);
    }
}
