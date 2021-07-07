package com.eshop.backend.controller;

import com.eshop.backend.service.interfaces.AuthorService;
import com.eshop.backend.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorCrudController {

    private final AuthorService authorService;

    @Autowired
    public AuthorCrudController(AuthorService authorService) {this.authorService = authorService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AuthorModel> getById(@PathVariable("id")Long id) {
        AuthorModel author = authorService.getById(id);
        return new ResponseEntity(author, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<AuthorModel> getAllAuthors() {
        List<AuthorModel> authors = authorService.getAll();
        return new ResponseEntity(authors, HttpStatus.OK);
    }
}
