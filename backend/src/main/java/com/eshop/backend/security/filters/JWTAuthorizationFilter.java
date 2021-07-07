package com.eshop.backend.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eshop.backend.repository.interfaces.AuthorizedUserDao;
import com.eshop.backend.security.jwt.JwtCreator;
import com.eshop.backend.model.AuthorizedUserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static com.eshop.backend.constants.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthorizedUserDao authorizedUserDao;

    public JWTAuthorizationFilter(AuthenticationManager authManager, AuthorizedUserDao authorizedUserDao) {
        super(authManager);
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, java.io.IOException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX) || header.equals(TOKEN_PREFIX + "null")) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + JwtCreator.createJwt((String) (authentication != null ? authentication.getPrincipal() : null)));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                AuthorizedUserModel authorizedUserModel = authorizedUserDao.getByLogin(user);
                ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
                list.add(new SimpleGrantedAuthority("ROLE_" + authorizedUserModel.getUserStatus()));
                list.add(new SimpleGrantedAuthority("ROLE_" + authorizedUserModel.getUserRole()));
                return new UsernamePasswordAuthenticationToken(user, null, list);
            }
            return null;
        }
        return null;
    }
}
