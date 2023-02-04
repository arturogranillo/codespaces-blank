package com.example.demo.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Abono {
    
    @Size(min=9, max = 9, message = "Las cuentas son de 9 digitos")
    private String cuenta;
    @Positive(message = "El monto debe ser positivo")
    private double monto;
}
