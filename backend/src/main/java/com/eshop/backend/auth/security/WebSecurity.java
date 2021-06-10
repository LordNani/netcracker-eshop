package com.eshop.backend.auth.security;

import com.eshop.backend.dao.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.auth.jwt.JWTAuthenticationFilter;
import com.eshop.backend.auth.jwt.JWTAuthorizationFilter;
import com.eshop.backend.auth.jwt.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static com.eshop.backend.auth.jwt.SecurityConstants.HEADER_STRING;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorizedUserDao authorizedUserdao;

    private String[] allowedURIs = new String[] {"/", "/v2/api-docs/**", "/configuration/ui/**",
            "/swagger-resources/**", "/swagger-ui.html*", "/webjars/**",
            "/actuator/*", "/localizations/**"};

    public WebSecurity(UserDetailsServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorizedUserDao authorizedUserdao) {
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorizedUserdao = authorizedUserdao;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        return httpSecurity -> httpSecurity.authorizeRequests()
//       // Allow access without authentication to URIs from the array.
//                .antMatchers(allowedOnlyForM2M).hasRole("M2M")
//                .antMatchers(ALL_PATH).permitAll()  // No authentication required for all URI paths
//                .and().logout().permitAll(false);            // Deny all paths after logout


        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/catalog/**").permitAll()
                .antMatchers(allowedURIs).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), authorizedUserdao));

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList(HEADER_STRING, "Origin", "Content-Type", "Accept",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
