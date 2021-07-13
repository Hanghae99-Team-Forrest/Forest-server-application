package com.forrest.server.web.controller;

import javax.xml.ws.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/07/13
 */


@RequestMapping("/api/hello")
@RestController
public class GreetingController {

    @GetMapping("")
    public ResponseEntity<String> hello () {
        return ResponseEntity.ok("Hello");
    }
}
