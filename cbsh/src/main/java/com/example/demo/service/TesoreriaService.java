package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ITesoreriaService;

import org.springframework.beans.factory.annotation.Autowired;

public class TesoreriaService implements ITesoreriaService {

  private IMovimientoRepository movimientoRepository;

  private ICuentaRepository cuentaRepository;

  @Autowired
  public TesoreriaService(IMovimientoRepository movimientoRepository, ICuentaRepository cuentaRepository) {
    this.movimientoRepository = movimientoRepository;
    this.cuentaRepository = cuentaRepository;
  }

  @Override
  public List<Movimiento> MovimientosPorCuenta(String numero){
    Optional<Cuenta> cuenta = cuentaRepository.findByNumero(numero);
    if(cuenta.isEmpty()){
      return null;
    }
    List<Movimiento> movimientos = movimientoRepository.findByIdCuenta(cuenta.get().getId());
    return movimientos;
  }

  @Override
  public boolean Transferencia(Transferencia transferencia){
    return false;
  }

  @Override
  public boolean Abono(String numero, String descripcion, BigDecimal monto){
    return false;
  }

  @Override
  public boolean Cargo(String numero, String descripcion, BigDecimal monto){
    return false;
  }

}