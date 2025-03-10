package com.administrador.api_gerencia.security.controller;

import com.administrador.api_gerencia.security.dto.AuthenticationDTO;
import com.administrador.api_gerencia.security.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authenticationDTO) {
        return ResponseEntity.ok(service.login(authenticationDTO));
    }

}
