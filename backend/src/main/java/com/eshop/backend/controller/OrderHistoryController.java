package com.eshop.backend.controller;

import com.eshop.backend.model.OrderCartModel;
import com.eshop.backend.service.interfaces.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderCartModel>> getAllOrders(@RequestParam("id") Long id,
                                                             @RequestParam("page") int page,
                                                             @RequestParam("size") int size) {
        List<OrderCartModel> orderHistory = orderHistoryService.getAllByUserId(id, page, size);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Long> getProductCount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderHistoryService.getOrderCount(id), HttpStatus.OK);
    }

}
