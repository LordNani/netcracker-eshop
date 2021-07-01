package com.eshop.backend.admin;


import com.eshop.backend.admin.services.AdminService;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/admin/search/")
    public ResponseEntity<List<AuthorizedUserModel>> getAllProduct() {
         List<AuthorizedUserModel> AuthorizedUserModel = adminService.getAllUsers();
    return  new ResponseEntity<>(AuthorizedUserModel, HttpStatus.OK);
    };
    @GetMapping("/admin/search/manager")
    public ResponseEntity<List<AuthorizedUserModel>> getAllManager(){
        List<AuthorizedUserModel> authorizedManager =  adminService.getAllManager();
        return new ResponseEntity<>(authorizedManager, HttpStatus.OK);
    }
    @GetMapping("/admin/search/courier")
    public ResponseEntity<List<AuthorizedUserModel>> getAllCourier(){
        List<AuthorizedUserModel> authorizedManager =  adminService.getAllCourier();
        return new ResponseEntity<>(authorizedManager, HttpStatus.OK);
    }
    @GetMapping("/admin/onDuty")
    public ResponseEntity<List<AuthorizedUserModel>> getFilteredByStatusOn(Model model){
        List<AuthorizedUserModel> authorizedManager = adminService.getFilteredByStatusOn();
        return new ResponseEntity<>(authorizedManager, HttpStatus.OK);
    }
//    @GetMapping("/admin/AllUsersMenu4")
//    public String getFilteredByStatusOff(Model model){
//        model.addAttribute("getFilteredByStatusOff", adminService.getFilteredByStatusOff());
//        return "getFilteredByStatusOff";
//    }
    @GetMapping("admin/getByName/{name}")
    public ResponseEntity<List<AuthorizedUserModel>> getByName(@PathVariable("name")String name) {
        List<AuthorizedUserModel> AuthorizedUserModel = adminService.getByName(name);
        return new ResponseEntity<>(AuthorizedUserModel, HttpStatus.OK);
    }

//    @GetMapping("/admin/AllUsersMenu")
//    public String getByName(Model model){
//        model.addAttribute("geByName",authorizeduserService.getByName());
//        return "geByName";
//    }
//    @GetMapping("/admin/AllUsersMenu")
//    public String getBySurname(Model model){
//        model.addAttribute("geBySurname",authorizeduserService.getBySurname());
//        return "geBySurname";
//    }
//    @GetMapping("/admin/AllUsersMenu")
//    public String getById(Model model){
//        model.addAttribute("getById",authorizeduserService.getById());
//        return "getById";




}
