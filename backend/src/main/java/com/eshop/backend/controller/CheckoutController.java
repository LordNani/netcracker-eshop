package com.eshop.backend.controller;

import com.eshop.backend.dto.OrderCheckoutDto;
import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.model.AuthorizedUserModel;
import com.eshop.backend.service.interfaces.CheckoutService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final AuthorizedUserDao authorizedUserDao;

    @Autowired
    public CheckoutController(CheckoutService checkoutService, AuthorizedUserDao authorizedUserDao) {
        this.checkoutService = checkoutService;
        this.authorizedUserDao = authorizedUserDao;
    }

    @GetMapping("/hours")
    public ResponseEntity<?> getDeliveryHours(@RequestParam("date") String date) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Date deliveryDate = objectMapper.readValue(date, Date.class);

        List<Integer> deliveryHours = checkoutService.getDeliveryHours(deliveryDate);

        return new ResponseEntity(deliveryHours, HttpStatus.OK);
    }

    @GetMapping("/user-info/{id}")
    public ResponseEntity<AuthorizedUserModel> getUserInformation(@PathVariable("id")Long id) {
        AuthorizedUserModel userInformation = authorizedUserDao.getById(id);
        return new ResponseEntity<>(userInformation, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderCheckoutDto orderCheckoutDto) {

        checkoutService.updateOrderCart(orderCheckoutDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
