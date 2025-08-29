package com.localapp.auth.login.controllers;

import com.localapp.auth.login.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class TokenController {

    @GetMapping("/public")
    public ResponseEntity<String> getPublicKey() {
        return ResponseEntity.ok(TokenService.getJWKS());
    }
}
