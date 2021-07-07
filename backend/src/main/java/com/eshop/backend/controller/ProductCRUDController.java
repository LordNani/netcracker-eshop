package com.eshop.backend.controller;

import com.eshop.backend.model.FilterModel;
import com.eshop.backend.model.ProductModel;
import com.eshop.backend.service.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductModel> getById(@PathVariable("id") Long id) {
        ProductModel productModel = productService.getById(id);
        return new ResponseEntity<>(productModel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody(required = false) ProductModel productModel) {
        productService.create(productModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel) {
        productService.update(productModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all-searched-ordered-filtered")
    public ResponseEntity<List<ProductModel>> getAllProductSearchedOrderedFiltered(@RequestParam("page") int page,
                                                                                   @RequestParam("size") int size,
                                                                                   @RequestParam("search") String search,
                                                                                   @RequestParam("orderBy") String orderBy,
                                                                                   @RequestParam("filters") String filters,
                                                                                   @RequestParam("isActive") Boolean isActive) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FilterModel filterModel = objectMapper.readValue(filters, FilterModel.class);
        List<ProductModel> productModels = productService.getSearchedOrderedFiltered(page, size, search, orderBy, filterModel, isActive);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("get-number-of-searched-ordered-filtered")
    public ResponseEntity<Integer> getNumberOfSearchedOrderedFiltered(@RequestParam("search") String search,
                                                                      @RequestParam("orderBy") String orderBy,
                                                                      @RequestParam("filters") String filters,
                                                                      @RequestParam("isActive") Boolean isActive) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FilterModel filterModel = objectMapper.readValue(filters, FilterModel.class);
        Integer amount = productService.getNumberOfSearchedOrderedFiltered(search, orderBy, filterModel, isActive);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

    @GetMapping("get-categories-of-product")
    public ResponseEntity<List<String>> getCategoriesOfProduct(@RequestParam("author") int author,
                                                               @RequestParam("cover-type") int coverType,
                                                               @RequestParam("genre") int genre,
                                                               @RequestParam("language") int language,
                                                               @RequestParam("publisher") int publisher) {
        List<String> categories = productService.getCategoriesOfProduct(author, coverType, genre, language, publisher);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/get-popular")
    public ResponseEntity<List<ProductModel>> getPopular(@RequestParam("page") int page,
                                                         @RequestParam("size") int size) {
        List<ProductModel> productModels = productService.getPopular(page, size);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("/get-new")
    public ResponseEntity<List<ProductModel>> getNew(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        List<ProductModel> productModels = productService.getNew(page, size);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

}


