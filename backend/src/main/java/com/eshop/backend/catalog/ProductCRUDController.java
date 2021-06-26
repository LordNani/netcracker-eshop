package com.eshop.backend.catalog;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.catalog.service.ProductService;
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
    public ResponseEntity<ProductModel> getById(@PathVariable("id")Long id) {
        ProductModel productModel = productService.getById(id);
        return new ResponseEntity(productModel, HttpStatus.OK);
    }


    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<ProductModel>> getById(@PathVariable("name")String name) {
        List<ProductModel> productModels = productService.getByName(name);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<ProductModel>> getAllProduct(@RequestParam("page")int page,
                                                            @RequestParam("size")int size) {
        List<ProductModel> productModels = productService.getProductPage(page, size);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody (required=false) ProductModel productModel) {
        productService.create(productModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel) {
        productService.update(productModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all-filter-order")
    public ResponseEntity<List<ProductModel>> getAllProductWithFilterAndOrderBy(@RequestParam("page")int page,
                                                                                @RequestParam("size")int size,
                                                                                @RequestParam("filter")List<String> filter,
                                                                                @RequestParam("orderBy")String orderBy) {
        List<ProductModel> productModels = productService.getAllOrderByWithFilters(page, size, orderBy, filter);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("/get-all-order")
    public ResponseEntity<List<ProductModel>> getAllProductOrderBy(@RequestParam("page")int page,
                                                                   @RequestParam("size")int size,
                                                                   @RequestParam("orderBy")String orderBy) {
        List<ProductModel> productModels = productService.getAllOrderBy(page, size, orderBy);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount() {
        return new ResponseEntity<>(productService.getProductCount(), HttpStatus.OK);
    }



}
