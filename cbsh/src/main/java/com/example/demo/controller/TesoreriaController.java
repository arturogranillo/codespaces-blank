package com.example.demo.controller;

import com.example.demo.model.Abono;
import com.example.demo.model.Cuenta;
import com.example.demo.model.Retiro;
import com.example.demo.model.Transferencia;
import com.example.demo.repository.ICuentaRepository;
import com.example.demo.service.ITesoreriaService;
import com.example.demo.service.TokenService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import com.example.demo.util.Result;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/tesoreria")
@SecurityRequirement(name = "bearer-key")
public class TesoreriaController {

  private ITesoreriaService tesoreriaService;
  private TokenService tokenService;

  @Autowired
  public TesoreriaController(ITesoreriaService tesoreriaService, TokenService tokenService) {
    this.tesoreriaService = tesoreriaService;
    this.tokenService = tokenService;
  }

  @PostMapping("/abono")
  public ResponseEntity abono(Authentication authentication, @Valid @RequestBody Abono abono) {

    Result validarUsuario = tokenService.validarToken(authentication);
    if (!validarUsuario.fueExitoso()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validarUsuario.getMensajeError());
    }

    Result<Cuenta> resultadoAbono = tesoreriaService.Abono(abono.getCuenta(), abono.getMonto());
    if (!resultadoAbono.fueExitoso()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(resultadoAbono.getMensajeError());
    }

    return ResponseEntity.status(HttpStatus.OK).body(resultadoAbono.getResultado());
  }

  @PostMapping("/retiro")
  public ResponseEntity retiro(Authentication authentication, @RequestBody Retiro retiro) {

    Result validarUsuario = tokenService.validarToken(authentication);
    if (!validarUsuario.fueExitoso()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validarUsuario.getMensajeError());
    }

    Result<Cuenta> resultadoAbono = tesoreriaService.Retiro(retiro.getCuenta(), retiro.getMonto());

    if (!resultadoAbono.fueExitoso()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(resultadoAbono.getMensajeError());
    }

    return ResponseEntity.status(HttpStatus.OK).body(resultadoAbono.getResultado());
  }

  @PostMapping("/transferencia")
  public ResponseEntity transferencia(Authentication authentication, @RequestBody Transferencia transferencia) {

    Result validarUsuario = tokenService.validarToken(authentication);
    if (!validarUsuario.fueExitoso()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validarUsuario.getMensajeError());
    }

    Result<Cuenta> resultadoAbono = tesoreriaService.Transferencia(transferencia);
    if (!resultadoAbono.fueExitoso()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(resultadoAbono.getMensajeError());
    }

    return ResponseEntity.status(HttpStatus.OK).body(resultadoAbono.getResultado());
  }
}