package com.example.demo.model;

import lombok.Data;
import java.util.Map;

@Data
public class RespuestaError {
    private int estatus;
    private Map<String,String> errores;
    private String ruta;
}
