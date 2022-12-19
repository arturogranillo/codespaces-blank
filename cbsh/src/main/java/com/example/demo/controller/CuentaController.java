package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private ICuentaRepository cuentaRepository;

    @Autowired
    public CuentaController(ICuentaRepository cuentaRepository) {
      this.cuentaRepository = cuentaRepository;
    }

    @GetMapping("/{numero}")
    public ResponseEntity obtenerCuenta(@PathVariable String numero) {

        Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
        if(posibleCuenta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("La cuenta indicada no existe.");
        }

        Cuenta cuenta = posibleCuenta.get();
        return ResponseEntity.status(HttpStatus.OK).body(cuenta);
    }

    @PostMapping
    public ResponseEntity crearCuenta(@RequestBody Cuenta cuenta) {

        Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(cuenta.getNumero());
        if(!posibleCuenta.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La cuenta indicada ya existe.");
        }

        cuentaRepository.save(cuenta);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cuenta);
    }

    @PutMapping("/{numero}")
    public ResponseEntity actualizarCuenta(@PathVariable String numero, @RequestBody Cuenta cuenta) {

        Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
        if(posibleCuenta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("La cuenta indicada no existe.");
        }

        Cuenta cuentaDB = posibleCuenta.get();
        cuentaDB.setNumero(cuenta.getNumero());
        cuentaDB.setSaldo(cuenta.getSaldo());
        cuentaRepository.save(cuentaDB);
        
        return ResponseEntity.status(HttpStatus.OK).body(cuentaDB);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity eliminarCuenta(@PathVariable String numero) {

        Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
        if(posibleCuenta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("La cuenta indicada no existe.");
        }

        Cuenta cuenta = posibleCuenta.get();

        if(cuenta.getSaldo() > 0){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede eliminar una cuenta con salfdo.");
        }

        cuentaRepository.delete(cuenta);
        
        return ResponseEntity.status(HttpStatus.OK).body("HTTP Status will be OK (CODE 200)\n");
    }

    @GetMapping("/todas")
    public ResponseEntity obtenerTodas() {

        List<Cuenta> cuentas = cuentaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cuentas);
    }
}
