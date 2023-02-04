package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.example.demo.util.Result;

import com.example.demo.model.*;

public interface ITesoreriaService {

  Result<Cuenta> Transferencia(Transferencia transferencia);

  Result<Cuenta> Abono(String numero, double monto);

  Result<Cuenta> Retiro(String numero, double monto);

}