package com.eshop.backend.auth.controllers;
import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDaoImpl;
import com.eshop.backend.DAO.Models.Product;
import com.eshop.backend.auth.dto.LoginRequstDTO;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
            AuthorizedUser user = authorizedUserDao.getByLogin(request.getEmail());

            if (user != null && bCryptPasswordEncoder.matches(request.getPassword(), user.getUserPassword())){
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/role")
    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
        String ss = bCryptPasswordEncoder.encode("MANAGER");
        String ss2 = bCryptPasswordEncoder.encode("ADMIN");
        String ss3 = bCryptPasswordEncoder.encode("COURIER");
        AuthorizedUser user = authorizedUserDao.getRoleByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
