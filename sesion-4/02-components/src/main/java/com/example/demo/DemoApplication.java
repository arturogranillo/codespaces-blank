package com.example.demo;

import com.example.demo.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  private Person person;

  public DemoApplication(Person person) {
    this.person = person;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @PostConstruct
  public void init() {
    person.setName("Arturo");
  }

  @Override
  public void run(String[] args) {
    System.out.println(person.greet("Gabriel"));
  }
}