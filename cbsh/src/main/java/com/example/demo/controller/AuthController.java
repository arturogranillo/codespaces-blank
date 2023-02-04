package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.util.Result;
import com.example.demo.service.TokenService;

@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity token(Usuario usuario) {
        Result<String> generarToken = tokenService.generateToken(usuario);

        if(!generarToken.fueExitoso()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(generarToken.getMensajeError());
        }
    
        return ResponseEntity.status(HttpStatus.OK).body(generarToken.getResultado());
    }

}
