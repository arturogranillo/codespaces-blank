package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ITesoreriaService;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.util.Result;

import org.springframework.stereotype.Service;

@Service
public class TesoreriaService implements ITesoreriaService {

  private ICuentaRepository cuentaRepository;

  @Autowired
  public TesoreriaService(ICuentaRepository cuentaRepository) {
    this.cuentaRepository = cuentaRepository;
  }

  @Override
  public Result<Cuenta> Transferencia(Transferencia transferencia){

    if(transferencia.getMonto() <= 0){
      return Result.fallo("El monto debe ser mayor a cero.");
    }

    Optional<Cuenta> posibleCuentaOrigen = cuentaRepository.findByNumero(transferencia.getCuentaOrigen());
    if(posibleCuentaOrigen.isEmpty()){
      return Result.fallo("La cuenta origen no existe.");
    }

    Optional<Cuenta> posibleCuentaDestino = cuentaRepository.findByNumero(transferencia.getCuentaDestino());
    if(posibleCuentaDestino.isEmpty()){
      return Result.fallo("La cuenta destino no existe.");
    }

    Cuenta cuentaOrigen = posibleCuentaOrigen.get();
    Cuenta cuentaDestino = posibleCuentaDestino.get();

    if(cuentaOrigen.getSaldo() < transferencia.getMonto()){
      return Result.fallo("La cuenta origen no tiene saldo suficiente");
    }

    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - transferencia.getMonto());
    cuentaRepository.save(cuentaOrigen);

    cuentaDestino.setSaldo(cuentaDestino.getSaldo() + transferencia.getMonto());
    cuentaRepository.save(cuentaDestino);
    
    return Result.exito(cuentaOrigen);
  }

  @Override
  public Result<Cuenta> Abono(String numero, double monto){

    if(monto <= 0){
      return Result.fallo("El monto debe ser mayor a cero.");
    }

    Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
    if(posibleCuenta.isEmpty()){
      return Result.fallo("La cuenta indicada no existe.");
    }

    Cuenta cuenta = posibleCuenta.get();
    cuenta.setSaldo(cuenta.getSaldo() + monto);
    cuentaRepository.save(cuenta);

    return Result.exito(cuenta);

  }

  @Override
  public Result<Cuenta> Retiro(String numero, double monto){

    if(monto <= 0){
      return Result.fallo("El monto debe ser mayor a cero.");
    }

    Optional<Cuenta> posibleCuenta = cuentaRepository.findByNumero(numero);
    if(posibleCuenta.isEmpty()){
      return Result.fallo("La cuenta indicada no existe.");
    }

    Cuenta cuenta = posibleCuenta.get();

    if(cuenta.getSaldo() < monto){
      return Result.fallo("La cuenta no tiene saldo suficiente.");
    }

    cuenta.setSaldo(cuenta.getSaldo() - monto);
    cuentaRepository.save(cuenta);

    return Result.exito(cuenta);
  }

}