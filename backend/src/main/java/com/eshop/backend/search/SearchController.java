package com.eshop.backend.search;


import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.mail.EmailSenderService;
import com.eshop.backend.utils.Employee.EmployeeService;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
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
        Long UserID =  employeeService.getIdByName(authorizedUserModel);
        if (authorizedUserModel.getUserRole().equals("COURIER")){
         employeeService.setForCouriers(authorizedUserModel,UserID);
        }
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



