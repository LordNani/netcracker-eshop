package com.eshop.backend.auth.jwt;

import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.eshop.backend.auth.jwt.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        //Sets the URL that determines if authentication is required
        setFilterProcessesUrl("/user/token");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AuthorizedUserModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AuthorizedUserModel.class);



            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserLogin(),
                            creds.getUserPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        //create token
        String token = JwtCreator.createJwt(((User) auth.getPrincipal()).getUsername());

        // put token in header "Authorization: Bearer ...tokenText"
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

    }
}
