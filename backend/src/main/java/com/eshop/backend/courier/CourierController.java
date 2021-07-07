package com.eshop.backend.courier;

import com.eshop.backend.courier.dto.calendar;
import com.eshop.backend.courier.dto.courierDto;
import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.courier.service.CourierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Controller
public class CourierController {
    @Autowired
    CourierService courierService;

    @GetMapping("/courier/cabinet/get/")
        public ResponseEntity<?> getMyTimeTable(@RequestParam("calendar") String filters) throws JsonProcessingException, ParseException {

            ObjectMapper objectMapper = new ObjectMapper();

            calendar filterModel = objectMapper.readValue(filters, calendar.class);

            Long Myid = filterModel.getId();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date SS =format.parse(filterModel.getDate());
        Date date = format.parse(filterModel.getDate());

            List<CourierModel> courierModel = courierService.getMyTimeTable(Myid,date);
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
