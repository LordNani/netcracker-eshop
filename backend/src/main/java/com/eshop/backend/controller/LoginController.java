package com.eshop.backend.controller;

import com.eshop.backend.dto.LoginRequestDTO;
import com.eshop.backend.service.implementations.LoginServiceIml;
import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    private final LoginServiceIml loginServiceIml;

    @Autowired
    public LoginController(LoginServiceIml loginServiceIml) {
        this.loginServiceIml = loginServiceIml;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDTO request) {
        AuthorizedUserModel user = loginServiceIml.login(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/role")
    public ResponseEntity<?> getUserRole(@RequestParam String login) {
        AuthorizedUserModel user = loginServiceIml.getUserRole(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
