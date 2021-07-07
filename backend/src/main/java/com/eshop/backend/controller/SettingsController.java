package com.eshop.backend.controller;

import com.eshop.backend.model.AuthorizedUserModel;
import com.eshop.backend.dto.UserInfoDTO;
import com.eshop.backend.service.interfaces.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    SettingsService settingsService;

    @Autowired
    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/info/{login}")
    public ResponseEntity<AuthorizedUserModel> getUserInformation(@PathVariable("login")String login) {
        AuthorizedUserModel userInformation = settingsService.getByLogin(login);
        return new ResponseEntity<>(userInformation, HttpStatus.OK);
    }

    @PutMapping("/info/update")
    public ResponseEntity<?> updateUserInformation(@RequestBody UserInfoDTO userInfoDTO) {
        settingsService.update(
                AuthorizedUserModel.builder()
                .id(userInfoDTO.getId())
                .userLogin(userInfoDTO.getUserLogin())
                .userName(userInfoDTO.getUserName())
                .userSurname(userInfoDTO.getUserSurname())
                .userAddress(userInfoDTO.getUserAddress())
                .userNumber(userInfoDTO.getUserNumber())
                .build()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
