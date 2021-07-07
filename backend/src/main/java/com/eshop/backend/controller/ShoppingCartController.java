package com.eshop.backend.controller;

import com.eshop.backend.model.ProductModel;
import com.eshop.backend.service.interfaces.ShoppingCartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/create-order")
    public ResponseEntity<Long> getById(@RequestParam("products") String orderProducts,
                                        @RequestParam("userId") Long userId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, ProductModel.class);

        List<ProductModel> productModel = objectMapper.readValue(orderProducts, collectionType);
        userId = shoppingCartService.createOrder(productModel, userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @DeleteMapping("/remove-order-product")
    public ResponseEntity<?> removeProductFromOrder(@RequestParam("product") String orderProduct,
                                                    @RequestParam("userId") Long userId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProductModel productModel = objectMapper.readValue(orderProduct, ProductModel.class);

        shoppingCartService.deleteProductFromOrder(productModel, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/shopping-cart")
    public ResponseEntity<List<ProductModel>> getShoppingCart(@RequestParam("userId") Long userId){

        List<ProductModel> productInShoppingCart = shoppingCartService.getProductInCart(userId);

        return new ResponseEntity<>(productInShoppingCart, HttpStatus.OK);
    }

    @GetMapping("/add-product-to-cart")
    public ResponseEntity<Long> addProductToShoppingCart(@RequestParam("product") String orderProduct,
                                                         @RequestParam("userId") Long userId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProductModel productModel = objectMapper.readValue(orderProduct, ProductModel.class);

        userId = shoppingCartService.addProductToShoppingCart(productModel, userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
