package com.solvex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.solvex.entity")
@EnableJpaRepositories("com.solvex.repository")
public class SolveXApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolveXApplication.class, args);
    }
}