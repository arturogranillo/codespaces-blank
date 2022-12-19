package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.example.demo.model.*;

public interface ITesoreriaService {

  List<Movimiento> MovimientosPorCuenta(String numero);

  boolean Transferencia(Transferencia transferencia);

  boolean Abono(String numero, String descripcion, BigDecimal monto);

  boolean Cargo(String numero, String descripcion, BigDecimal monto);

}