package com.example.demo.controller;

import java.util.List;
import com.example.demo.model.Person;
import com.example.demo.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  private IPersonRepository repository;

  @Autowired
  public PersonController(IPersonRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/getAll")
  public List<Person> getAll() {
    return repository.findAll();
  }
}