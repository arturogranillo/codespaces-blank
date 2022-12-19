package com.example.demo.controller;

import com.example.demo.model.Cuenta;
import com.example.demo.model.Transferencia;
import com.example.demo.repository.ICuentaRepository;
import com.example.demo.service.ITesoreriaService;
import com.example.demo.model.util.Result;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/tesoreria")
public class TesoreriaController {

    private ICuentaRepository cuentaRepository;

    private ITesoreriaService tesoreriaService;

    @Autowired
    public TesoreriaController(ICuentaRepository cuentaRepository, ITesoreriaService tesoreriaService) {
        this.cuentaRepository = cuentaRepository;
        this.tesoreriaService = tesoreriaService;
    }

    @GetMapping("/abono/{numero}/{monto}")
    public ResponseEntity abono(@PathVariable String numero, @PathVariable double monto) {

    Result<Cuenta> resultadoAbono = tesoreriaService.Abono(numero, monto);

    if(!resultadoAbono.fueExitoso()){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resultadoAbono.getMensajeError());
    }

    return ResponseEntity.status(HttpStatus.OK).body(resultadoAbono.getResultado());
  }

  @GetMapping("/retiro/{numero}/{monto}")
  public ResponseEntity retiro(@PathVariable String numero, @PathVariable double monto) {

    Result<Cuenta> resultadoAbono = tesoreriaService.Retiro(numero, monto);

    if(!resultadoAbono.fueExitoso()){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resultadoAbono.getMensajeError());
    }

    return ResponseEntity.status(HttpStatus.OK).body(resultadoAbono.getResultado());
  }

  @PutMapping("/transferencia")
  public ResponseEntity transferencia(@RequestBody Transferencia transferencia) {

    Result<Cuenta> resultadoAbono = tesoreriaService.Transferencia(transferencia);
    if(!resultadoAbono.fueExitoso()){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resultadoAbono.getMensajeError());
    }

    return ResponseEntity.status(HttpStatus.OK).body(resultadoAbono.getResultado());
  }
}