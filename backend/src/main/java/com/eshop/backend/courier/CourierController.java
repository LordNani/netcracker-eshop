package com.eshop.backend.courier;

import com.eshop.backend.courier.dto.calendar;
import com.eshop.backend.courier.dto.courierDto;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.courier.service.CourierService;
import com.eshop.backend.product.dao.models.FilterModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CourierController {
    @Autowired
    CourierService courierService;

    @GetMapping("/courier/cabinet/get/")
    public ResponseEntity<List<CourierModel>> getMyTimeTable(@RequestParam("calendar") String filters)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        calendar filterModel = objectMapper.readValue(filters, calendar.class);
        SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/YYYY");
        String dateString = formatter.format(filterModel.getDate());
        List<CourierModel> courierModel = courierService.getMyTimeTable(filterModel.getId(),dateString);
        return new ResponseEntity<>(courierModel, HttpStatus.OK);
    }
    @GetMapping("/courier/cabinet/set/")
    public ResponseEntity<?> setOrderCartStatus(@RequestParam("courierDto") String filters) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        courierDto filterModel = objectMapper.readValue(filters, courierDto.class);
        courierService.setOrderCartStatus(filterModel.cartId, filterModel.status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
