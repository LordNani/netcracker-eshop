package com.eshop.backend.search;


import com.eshop.backend.utils.Employee.EmployeeService;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

//import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final EmployeeService employeeService;
    @Autowired
    public SearchController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<AuthorizedUserModel>> getAllEmployees(){
        List<AuthorizedUserModel> emp = employeeService.getEmployees();
        return new ResponseEntity<>(emp, HttpStatus.OK);

    }
    @CrossOrigin
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody AuthorizedUserModel authorizedUserModel) {

        employeeService.createEmployee(authorizedUserModel);
        return new ResponseEntity<>("Employee is created", HttpStatus.CREATED);
        //return "New employee is successfully added";

    }


//    @GetMapping("/test")
//    public ResponseEntity<?> test() {
//        Employee user = new Employee();
//        user.setFirstName("testLogin");
//        user.setLastName("some password");
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete( @PathVariable ("id")Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee is deleted",HttpStatus.OK);

        }

//        @PutMapping("/edit")
//        public ResponseEntity<?> edit(@RequestBody Employee employee){
//            employeeService.editEmployee(employee);
//            return new ResponseEntity<>("Employee is updated",HttpStatus.OK);
//
//        }
    }



