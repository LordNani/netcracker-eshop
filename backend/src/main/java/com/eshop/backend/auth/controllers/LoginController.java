package com.eshop.backend.auth.controllers;
import com.eshop.backend.dao.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.dao.DataAccess.AuthorizedUser.AuthorizedUserDaoImpl;
import com.eshop.backend.auth.dto.LoginRequstDTO;
import com.eshop.backend.dao.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthorizedUserDao authorizedUserdao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(AuthorizedUserDaoImpl authorizedUserdao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorizedUserdao = authorizedUserdao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequstDTO request) {
        try {
            AuthorizedUser user = authorizedUserdao.getByLogin(request.getEmail());

            if (user != null && bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/user/role")
    public ResponseEntity<?> getSortedProduct(@RequestParam String login) {
        AuthorizedUser user = authorizedUserdao.getRoleByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
