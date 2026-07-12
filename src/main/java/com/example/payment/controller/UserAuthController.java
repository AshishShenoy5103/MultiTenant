package com.example.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    @GetMapping("/api/user/login")
    public ResponseEntity<?> userLogin() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
