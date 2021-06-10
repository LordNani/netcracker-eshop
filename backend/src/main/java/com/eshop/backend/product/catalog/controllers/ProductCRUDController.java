package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.dao.Models.Product;
import com.eshop.backend.product.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductCRUDController {

    private final ProductService productService;

    @Autowired
    public ProductCRUDController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id")Long id) {
        Product product = productService.getById(id);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Product>> getById(@PathVariable("name")String name) {
        List<Product> products = productService.getByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam("page")int page,
                                                       @RequestParam("size")int size) {
        List<Product> products = productService.getProductPage(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        productService.update(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all-filter-order")
    public ResponseEntity<List<Product>> getAllProductWithFilterAndOrderBy(@RequestParam("page")int page,
                                                                           @RequestParam("size")int size,
                                                                           @RequestParam("filter")List<String> filter,
                                                                           @RequestParam("orderBy")String orderBy) {
        List<Product> products = productService.getAllOrderByWithFilters(page, size, orderBy, filter);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/get-all-order")
    public ResponseEntity<List<Product>> getAllProductOrderBy(@RequestParam("page")int page,
                                                              @RequestParam("size")int size,
                                                               @RequestParam("orderBy")String orderBy) {
        List<Product> products = productService.getAllOrderBy(page, size, orderBy);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
