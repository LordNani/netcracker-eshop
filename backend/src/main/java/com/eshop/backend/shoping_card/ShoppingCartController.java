package com.eshop.backend.shoping_card;

import com.eshop.backend.product.dao.models.ProductModel;
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

    @GetMapping("/check-product-in-stock")
    public ResponseEntity<ProductModel> getById(@RequestParam("products") String orderProducts) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, ProductModel.class);

        List<ProductModel> productModel = objectMapper.readValue(orderProducts, collectionType);
        shoppingCartService.checkProductInStock(productModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}