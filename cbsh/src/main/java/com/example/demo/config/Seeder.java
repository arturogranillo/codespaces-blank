package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

import com.example.demo.model.Cuenta;
import com.example.demo.model.Movimiento;

import com.example.demo.repository.*;

import java.math.BigDecimal;

@Component
public class Seeder implements CommandLineRunner {

  private ICuentaRepository cuentaRepository;
  private IMovimientoRepository movimientoRepository;
  
  @Autowired
  public Seeder(ICuentaRepository cuentaRepository, IMovimientoRepository movimientoRepository) {
    this.cuentaRepository = cuentaRepository;
    this.movimientoRepository = movimientoRepository;
  }

  @Override
  public void run(String[] args) {
    Optional<Cuenta> cuenta = cuentaRepository.findByNumero("123456789");

    if (cuenta.isEmpty()) {
      Cuenta c = new Cuenta();
      c.setNumero("123456789");
      c.setSaldo(0.00);
      cuentaRepository.save(c);
    }

    List<Movimiento> movimientos =  movimientoRepository.findByIdCuenta(cuenta.get().getId());
    for (Movimiento m:movimientos) {
      movimientoRepository.delete(m);
    }
  }
}