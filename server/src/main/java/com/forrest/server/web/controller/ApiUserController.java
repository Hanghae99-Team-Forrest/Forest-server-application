package com.forrest.server.web.controller;

import com.forrest.server.service.ApiUserService;
import com.forrest.server.web.dto.request.SignUpReqDto;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class ApiUserController {

    private final ApiUserService apiUserService;

    @PostMapping("")
    public ResponseEntity<Void> signUp( @RequestBody @Valid SignUpReqDto signUpReqDto ) {
        apiUserService.signUp(signUpReqDto);
        return ResponseEntity.created(URI.create("/api/users")).build();
    }
}
