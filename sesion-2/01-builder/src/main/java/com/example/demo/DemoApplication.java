package com.example.demo;

import com.example.demo.Persona;
import com.example.demo.PersonaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception {
		Persona arturo = new PersonaBuilder("Arturo", "Sotelo", "Granillo")
			.estadoCivil("casado")
			.edad(30)
			.direccion("Chihuahua")
			.construir();
	
		System.out.println(arturo.toString());
	}
}
