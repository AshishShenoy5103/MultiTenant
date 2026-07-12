package com.example.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {
    @GetMapping("/login")
    public ResponseEntity<?> userLogin() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
