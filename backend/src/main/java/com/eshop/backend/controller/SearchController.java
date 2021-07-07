package com.eshop.backend.controller;


import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.service.implementations.EmailSenderService;
import com.eshop.backend.service.implementations.EmployeeService;

import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final EmployeeService employeeService;
    private final EmailSenderService emailSenderService;
    private final AuthorizedUserDao authorizedUserDao;

    @Autowired
    public SearchController(EmployeeService employeeService, EmailSenderService emailSenderService, AuthorizedUserDao authorizedUserDao) {
        this.employeeService = employeeService;
        this.emailSenderService = emailSenderService;
        this.authorizedUserDao = authorizedUserDao;
    }

    //    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<AuthorizedUserModel>> getAllEmployees() {
        List<AuthorizedUserModel> emp = employeeService.getEmployees();
        return new ResponseEntity<>(emp, HttpStatus.OK);

    }

    @CrossOrigin
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody AuthorizedUserModel authorizedUserModel) {
        employeeService.createEmployee(authorizedUserModel);
        emailSenderService.sendEmail(authorizedUserDao.getByLogin(authorizedUserModel.getUserLogin()), "resetPassword");
        return new ResponseEntity<>(authorizedUserModel, HttpStatus.CREATED);


    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        employeeService.getEmployees();
        List<AuthorizedUserModel> emp = employeeService.getEmployees();
        return new ResponseEntity<>(emp, HttpStatus.OK);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody AuthorizedUserModel authorizedUserModel, @PathVariable("id") Long id) {
        employeeService.editEmployee(authorizedUserModel);
        return new ResponseEntity<>(authorizedUserModel, HttpStatus.OK);

    }
}



