package com.example.demo2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo2")
@EnableOpenApi
public class AppConfiguration {
}