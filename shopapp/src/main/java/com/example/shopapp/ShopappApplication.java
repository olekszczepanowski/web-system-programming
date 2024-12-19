package com.example.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories

public class ShopappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopappApplication.class, args);
    }

}
