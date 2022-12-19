package com.example.demo2.controller;

import com.example.demo2.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.LinkedList;

@RestController
public class CursoController {

    private List<Curso> cursos;

    public CursoController() {
        this.cursos = new LinkedList<Curso>();
        cursos.add(new Curso(1l, "Matematicas"));
        cursos.add(new Curso(2l, "Español"));
        cursos.add(new Curso(3l, "Historia"));
        cursos.add(new Curso(4l, "Ciencias"));
        cursos.add(new Curso(5l, "Geografia"));
    }

    @GetMapping("/cursos")
    public List<Curso> cursos (){
        return cursos;
    }

    ////por objeto
    ////cursoObjeto/2
    @GetMapping("/cursoObjeto/{id}")
    public Curso cursoPorObjeto (PorId porId){
        return BuscarStream(porId.getId());
    }

    ////por ruta
    ////cursoRuta/2
    @GetMapping("/cursoRuta/{id}")
    public Curso cursoPorRuta (@PathVariable long id){
        return BuscarStream(id);
    }

    ////por query
    ////cursoQuery?id=3
    @GetMapping("/cursoQuery")
    public Curso cursosPorQuery (long id){
        return BuscarStream(id);
    }

    private Curso Buscar(long id){
        for(Curso curso : cursos) {
            if (curso.getId() == id){
                return curso;
            }
          }
        return null;
    }

    private Curso BuscarStream(long id){
        return cursos.stream()
            .filter(curso -> curso.getId().equals(id))
            .findAny()
            .orElse(null);
    }
}
