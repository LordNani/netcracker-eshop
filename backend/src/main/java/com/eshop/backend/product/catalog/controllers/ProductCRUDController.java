package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.DAO.Models.Product;
import com.eshop.backend.product.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCRUDController {

    private final ProductService productService;

    @Autowired
    public ProductCRUDController(ProductService productService) {
        this.productService = productService;
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
