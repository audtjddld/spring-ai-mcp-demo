package com.example.apihomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ApiHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiHomeworkApplication.class, args);
    }

}
