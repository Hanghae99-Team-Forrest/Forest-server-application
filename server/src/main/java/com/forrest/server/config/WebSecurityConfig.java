package com.forrest.server.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // TODO: 2021.07.11 -Blue  - 추후 권한 조건 만들기

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure ( WebSecurity web ) throws Exception {
        web
            .ignoring()
            .requestMatchers(
                PathRequest.toH2Console()
            );
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http
                .csrf().disable();
        http
                .authorizeRequests(request -> {
                    request.anyRequest().permitAll();  //todo 추후 인증 요건 추가하기
                })
                .formLogin().disable()
                .sessionManagement( session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
    }
}
