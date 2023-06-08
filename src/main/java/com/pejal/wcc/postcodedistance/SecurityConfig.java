/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author pejalhebat
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {        
        httpSecurity.csrf()
                    .disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/postcode/**")
                    .permitAll();
        return httpSecurity.build();
    }
}