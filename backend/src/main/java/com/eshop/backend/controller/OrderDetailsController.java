package com.eshop.backend.controller;

import com.eshop.backend.model.ProductModel;
import com.eshop.backend.service.interfaces.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-history")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<List<ProductModel>> getAllProductInOrder(@PathVariable("id") Long id) {
        List<ProductModel> products = orderDetailsService.getAllProductByOrderId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
