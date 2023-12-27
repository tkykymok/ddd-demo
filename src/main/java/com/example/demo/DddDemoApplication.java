package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
public class DddDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddDemoApplication.class, args);
    }

}
