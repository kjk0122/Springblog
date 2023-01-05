package com.example.kjkspringblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

    @SpringBootApplication
    @EnableJpaAuditing
public class KjkspringblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(KjkspringblogApplication.class, args);
    }

}
