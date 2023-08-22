package com.example.youtubeclone.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfiguration {

    /*@Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .oauth2ResourceServer().jwt();
    }*/
}
