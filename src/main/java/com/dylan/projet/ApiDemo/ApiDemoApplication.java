package com.dylan.projet.ApiDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.dylan.projet.ApiDemo.entities")
public class ApiDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }
}
