package com.eshop.backend.auth.controllers;

import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDaoImpl;
import com.eshop.backend.auth.DTO.LoginRequstDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthorizedUserDao authorizedUserDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(AuthorizedUserDaoImpl authorizedUserDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorizedUserDao = authorizedUserDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequstDTO request) {
        try {
            AuthorizedUser user = authorizedUserDao.getByLogin(request.getUserLogin());

            if (user != null && bCryptPasswordEncoder.matches(request.getUserPassword(), user.getUserPassword())){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }


}