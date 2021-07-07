package com.eshop.backend.controller;

import com.eshop.backend.service.interfaces.GenreService;
import com.eshop.backend.model.GenreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreCrudController {
    private final GenreService genreService;

    @Autowired
    public GenreCrudController(GenreService genreService) {this.genreService = genreService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<GenreModel> getById(@PathVariable("id")Long id) {
        GenreModel genre = genreService.getById(id);
        return new ResponseEntity(genre, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<GenreModel> getAllGenres() {
        List<GenreModel> genres = genreService.getAll();
        return new ResponseEntity(genres, HttpStatus.OK);
    }
}
