package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)  {
        return args -> {
            Student henk = new Student(
                    "Henk",
                    "henk.broodjeaap@gmail.com",
                    LocalDate.of(2000, APRIL, 5)
            );
            Student bert = new Student(
                    "Bert",
                    "bert@gmail.com",
                    LocalDate.of(2005, APRIL, 1)
            );

            repository.saveAll(
                    List.of(henk, bert)
            );
        };
    }
}
