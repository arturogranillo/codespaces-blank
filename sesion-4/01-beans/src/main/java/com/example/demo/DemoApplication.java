package com.example.demo;

import com.example.demo.config.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  private Greeting greeting;

  @Autowired
  public DemoApplication(Greeting gretting) {
    this.greeting = gretting;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String[] args) throws Exception {
    System.out.println(greeting.greet("Alex"));
  }
}