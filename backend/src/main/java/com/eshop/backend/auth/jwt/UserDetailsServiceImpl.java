package com.eshop.backend.auth.jwt;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.user.AuthorizedUserDaoImpl;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorizedUserDao authorizedUserDao;

    @Autowired
    public UserDetailsServiceImpl(AuthorizedUserDaoImpl authorizedUserDao) {
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AuthorizedUserModel user = authorizedUserDao.getByLogin(email);
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            return new User(user.getUserLogin(), user.getUserPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User " + email + " does not exist...");
        }
    }

}
