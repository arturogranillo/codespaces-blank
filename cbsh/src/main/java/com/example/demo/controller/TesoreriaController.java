package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/tesoreria")
public class TesoreriaController {

    private ICuentaRepository cuentaRepository;

    @Autowired
    public TesoreriaController(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @GetMapping("/abono/{numero}/{monto}")
    public ResponseEntity abono(@PathVariable String numero, @PathVariable double monto) {

    if(monto >= 0.01){
    return ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP Status will be CONFLICT (CODE 409)\n");
    }

    Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
    if(posibleCuenta.isEmpty()){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("HTTP Status will be NO_CONTENT (CODE 204)\n");
    }

    Cuenta cuenta = posibleCuenta.get();
    cuenta.setSaldo(cuenta.getSaldo() + monto);
    cuentaRepository.save(cuenta);

    return ResponseEntity.status(HttpStatus.OK).body(cuenta);
  }

  @GetMapping("/retiro/{numero}/{monto}")
  public ResponseEntity retiro(@PathVariable String numero, @PathVariable double monto) {

    if(monto >= 0.01){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP Status will be CONFLICT (CODE 409)\n");
    }

    Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
    if(posibleCuenta.isEmpty()){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("HTTP Status will be NO_CONTENT (CODE 204)\n");
    }

    Cuenta cuenta = posibleCuenta.get();
    cuenta.setSaldo(cuenta.getSaldo() - monto);
    cuentaRepository.save(cuenta);

    return ResponseEntity.status(HttpStatus.OK).body(cuenta);
  }

  @PutMapping("/transferencia")
  public ResponseEntity transferencia(@RequestBody Transferencia transferencia) {

    if(transferencia.getMonto() >= 0.01){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP Status will be CONFLICT (CODE 409)\n");
    }

    Optional<Cuenta> posibleCuentaOrigen = cuentaRepository.findByNumero(transferencia.getCuentaOrigen());
    if(posibleCuentaOrigen.isEmpty()){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("HTTP Status will be NO_CONTENT (CODE 204)\n");
    }

    Optional<Cuenta> posibleCuentaDestino = cuentaRepository.findByNumero(transferencia.getCuentaDestino());
    if(posibleCuentaDestino.isEmpty()){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("HTTP Status will be NO_CONTENT (CODE 204)\n");
    }

    Cuenta cuentaOrigen = posibleCuentaOrigen.get();
    Cuenta cuentaDestino = posibleCuentaDestino.get();

    if(cuentaOrigen.getSaldo() < transferencia.getMonto()){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP Status will be CONFLICT (CODE 409)\n");
    }

    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - transferencia.getMonto());
    cuentaRepository.save(cuentaOrigen);

    cuentaDestino.setSaldo(cuentaDestino.getSaldo() + transferencia.getMonto());
    cuentaRepository.save(cuentaDestino);
    
    return ResponseEntity.status(HttpStatus.OK).body("HTTP Status will be OK (CODE 200)\n");
  }
}