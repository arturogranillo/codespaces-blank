package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private ICuentaRepository cuentaRepository;

    @Autowired
    public CuentaController(ICuentaRepository cuentaRepository) {
      this.cuentaRepository = cuentaRepository;
    }

    @GetMapping("/{numero}")
    public Cuenta obtenerCuenta(@PathVariable String numero) {
        return cuentaRepository.findByNumero(numero).get();
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return new Cuenta();
    }

    @PutMapping("/{id}")
    public Cuenta actualizarCuenta(@PathVariable long id, @RequestBody Cuenta cuenta) {
        return new Cuenta();
    }

    @DeleteMapping("/{id}")
    public void eliminarCuenta(@PathVariable long id) {
        return;
    }
}
