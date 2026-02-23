package com.solvex;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.solvex.entity")
@EnableJpaRepositories("com.solvex.repository")
public class SolveXApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().directory("E:/Big Projects/SolveX").load();
        System.setProperty("spring.datasource.url",dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username",dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password",dotenv.get("DB_PASSWORD"));
        SpringApplication.run(SolveXApplication.class, args);
    }
}