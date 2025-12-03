package com.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan(basePackages = "com.prueba.model")
@EnableJpaRepositories(basePackages = "com.prueba.repository")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}