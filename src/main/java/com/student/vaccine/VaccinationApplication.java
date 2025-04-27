package com.student.vaccine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.student.vaccine.entity")
@EnableJpaRepositories("com.student.vaccine.repository")
public class VaccinationApplication {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SpringApplication.run(VaccinationApplication.class, args);
    }
}
