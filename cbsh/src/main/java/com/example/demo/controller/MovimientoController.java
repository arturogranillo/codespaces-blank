package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovimientoController {
  @GetMapping("/hola")
  public String holaMundo() {
    return "Hola mundo!";
  }
}
