package com.scaler.ecommerceuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceUserServiceApplication.class, args);
    }

}
