package com.eshop.backend.controller;


import com.eshop.backend.dto.adminDto;
import com.eshop.backend.service.interfaces.AdminService;
import com.eshop.backend.model.AuthorizedUserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/admin/search/")
    public ResponseEntity<List<AuthorizedUserModel>> getAllProduct() {
        List<AuthorizedUserModel> AuthorizedUserModel = adminService.getAllUsers();
        return new ResponseEntity<>(AuthorizedUserModel, HttpStatus.OK);
    }

    ;

    @GetMapping("admin/get")
    public ResponseEntity<List<AuthorizedUserModel>> getBy(@RequestParam("Admin") String filters) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        adminDto filterModel = objectMapper.readValue(filters, adminDto.class);
        List<AuthorizedUserModel> AuthorizedUserModel = adminService.getBy(filterModel);
        return new ResponseEntity<>(AuthorizedUserModel, HttpStatus.OK);
    }
}
