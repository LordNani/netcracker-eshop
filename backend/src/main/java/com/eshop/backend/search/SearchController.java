package com.eshop.backend.search;


import com.eshop.backend.DAO.DataAccess.Employee.EmployeeService;
import com.eshop.backend.DAO.Models.Employee;

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

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> emp = employeeService.getEmployees();
        return new ResponseEntity<>(emp, HttpStatus.OK);

    }
    @CrossOrigin
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody Employee employee) {

        employeeService.createEmployee(employee);
        return new ResponseEntity<>("Employee is created", HttpStatus.CREATED);
        //return "New employee is successfully added";

    }


    @GetMapping("/test")
    public ResponseEntity<?> test() {
        Employee user = new Employee();
        user.setFirstName("testLogin");
        user.setLastName("some password");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete( @PathVariable ("id")String id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee is deleted",HttpStatus.OK);

        }
        @CrossOrigin
        @PutMapping("/edit/{id}")
        public ResponseEntity<?> edit(@RequestBody Employee employee, String id){
            boolean status = employeeService.editEmployee(employee, id);
            if(status == true) {
                return new ResponseEntity<>("Employee is updated", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Such employee is not found", HttpStatus.NOT_FOUND);
            }
        }
        @GetMapping("/{id}")
        public ResponseEntity<?> getEmployee(@PathVariable ("id")String id){
            employeeService.getEmployee(id);
            return new ResponseEntity<>("Employee is found", HttpStatus.OK);

        }
    }



