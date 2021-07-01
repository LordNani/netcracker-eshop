package com.eshop.backend.catalog;

import com.eshop.backend.product.dao.models.FilterModel;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.catalog.service.ProductService;
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
        return new ResponseEntity(productModel, HttpStatus.OK);
    }


    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<ProductModel>> getById(@PathVariable("name") String name) {
        List<ProductModel> productModels = productService.getByName(name);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<ProductModel>> getAllProduct(@RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
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
    public ResponseEntity<List<ProductModel>> getAllProductWithFilterAndOrderBy(@RequestParam("page") int page,
                                                                                @RequestParam("size") int size,
                                                                                @RequestParam("filter") List<String> filter,
                                                                                @RequestParam("orderBy") String orderBy) {
        List<ProductModel> productModels = productService.getAllOrderByWithFilters(page, size, orderBy, filter);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("/get-all-order")
    public ResponseEntity<List<ProductModel>> getAllProductOrderBy(@RequestParam("page") int page,
                                                                   @RequestParam("size") int size,
                                                                   @RequestParam("orderBy") String orderBy) {
        List<ProductModel> productModels = productService.getAllOrderBy(page, size, orderBy);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("/get-all-filtered")
    public ResponseEntity<List<ProductModel>> getAllProductFiltered(@RequestParam("page") int page,
                                                                    @RequestParam("size") int size,
                                                                    @RequestParam("filters") String filters) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FilterModel filterModel = objectMapper.readValue(filters, FilterModel.class);
        List<ProductModel> productModels = productService.getFiltered(page, size, filterModel);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("/get-all-searched-ordered-filtered")
    public ResponseEntity<List<ProductModel>> getAllProductSearchedOrderedFiltered(@RequestParam("page") int page,
                                                                                   @RequestParam("size") int size,
                                                                                   @RequestParam("search") String search,
                                                                                   @RequestParam("orderBy") String orderBy,
                                                                                   @RequestParam("filters") String filters) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FilterModel filterModel = objectMapper.readValue(filters, FilterModel.class);
        List<ProductModel> productModels = productService.getSearchedOrderedFiltered(page, size, search, orderBy, filterModel);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    @GetMapping("get-number-of-searched-ordered-filtered")
    public ResponseEntity<Integer> getNumberOfSearchedOrderedFiltered(@RequestParam("search") String search,
                                                                      @RequestParam("orderBy") String orderBy,
                                                                      @RequestParam("filters") String filters) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        FilterModel filterModel = objectMapper.readValue(filters, FilterModel.class);
        Integer amount = productService.getNumberOfSearchedOrderedFiltered(search, orderBy, filterModel);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

    @GetMapping("get-categories-of-product")
    public ResponseEntity<List<String>> getCategoriesOfProduct(@RequestParam("author") int author,
                                                               @RequestParam("cover-type") int coverType,
                                                               @RequestParam("genre") int genre,
                                                               @RequestParam("language") int language,
                                                               @RequestParam("publisher") int publisher){
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


