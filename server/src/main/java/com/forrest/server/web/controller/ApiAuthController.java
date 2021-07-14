package com.forrest.server.web.controller;

import com.forrest.server.security.jwt.JwtFilter;
import com.forrest.server.security.jwt.TokenProvider;
import com.forrest.server.util.SecurityUtil;
import com.forrest.server.web.dto.request.LoginReqDto;
import com.forrest.server.web.dto.response.TokenResDto;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/07/13
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiAuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<TokenResDto> authorize (@Valid @RequestBody LoginReqDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // authenticate() 가 실행이 될 때 CustomUserService 의 loadByUsername 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<String> username = SecurityUtil.getCurrentUsername();

        String jwt = tokenProvider.createToken(authentication, username);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenResDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
