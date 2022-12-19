package com.example.demo2.controller;

import com.example.demo2.model.Curso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloWorld (){
        return "Hola Arturo";
    }

    @GetMapping("/goodbye")
    public String goobye (){
        return "Bye Arturo";
    }

    @GetMapping("/nuevocurso")
    public Curso enviarCurso (){
        return new Curso(1l, "Mathes");
    }
}
