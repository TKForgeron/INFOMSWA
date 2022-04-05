package com.trip.accountservice.subscription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class AccountConfig {
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository)  {
        return args -> {
            Account fred = new Account(
                    1L,
                    LocalDate.of(2050, APRIL, 5),
                    12346,
                    "NL12312442",
                    new Date()
            );

            repository.saveAll(
                    List.of(fred)
            );
        };
    }
}