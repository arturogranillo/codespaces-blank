package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

import com.example.demo.model.Cuenta;

import com.example.demo.repository.*;

import java.math.BigDecimal;

@Component
public class Seeder implements CommandLineRunner {

  private ICuentaRepository cuentaRepository;
  
  @Autowired
  public Seeder(ICuentaRepository cuentaRepository) {
    this.cuentaRepository = cuentaRepository;
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

  }
}