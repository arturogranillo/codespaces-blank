package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.example.demo.model.Cuenta;
import com.example.demo.model.Transferencia;
import com.example.demo.repository.ICuentaRepository;
import com.example.demo.service.TesoreriaService;
import com.example.demo.util.Result;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    @Mock
    ICuentaRepository cuentaRepository;
    
    @InjectMocks
    TesoreriaService tesoreriaService;

    @BeforeEach
    void setUp() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero("123456789");
        cuenta.setSaldo(100);
        Mockito.lenient().when(cuentaRepository.findByNumero("123456789")).thenReturn(Optional.empty());
    }

    @Test
    void abonoNegativo(){
        Result<Cuenta> result = tesoreriaService.Abono("123456789", -100);
        assertFalse(result.fueExitoso());
    }

    @Test
    void abonoCuentaInexistente(){
        Result<Cuenta> result = tesoreriaService.Abono("123456789", 100);
        assertFalse(result.fueExitoso());
    }

    @Test
    void retiroNegativo(){
        Result<Cuenta> result = tesoreriaService.Retiro("123456789", -100);
        assertFalse(result.fueExitoso());
    }

    @Test
    void retiroCuentaInexistente(){
        Result<Cuenta> result = tesoreriaService.Abono("123456789", 100);
        assertFalse(result.fueExitoso());
    }

    @Test
    void transferenciaNegativo(){
        Transferencia transferencia = new Transferencia();
        transferencia.setCuentaOrigen("123456789");
        transferencia.setCuentaDestino("987654321");
        transferencia.setMonto(-100);
        Result<Cuenta> result = tesoreriaService.Transferencia(transferencia);
        assertFalse(result.fueExitoso());
    }

    @Test
    void transferenciaCuentaInexistente(){
        Transferencia transferencia = new Transferencia();
        transferencia.setCuentaOrigen("123456789");
        transferencia.setCuentaDestino("987654321");
        transferencia.setMonto(-100);
        Result<Cuenta> result = tesoreriaService.Transferencia(transferencia);
        assertFalse(result.fueExitoso());
    }
}
