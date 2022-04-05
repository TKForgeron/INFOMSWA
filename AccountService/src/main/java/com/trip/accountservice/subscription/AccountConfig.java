package com.trip.accountservice.subscription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

import static java.time.Month.APRIL;

public class AccountConfig {
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository)  {
        return args -> {
            Account henk = new Account(
                    1L,
                    LocalDate.of(2050, APRIL, 5),
                    12345,
                    "NL12312441",
                    new Date(2022, 4, 5)
            );
            Account fred = new Account(
                    1L,
                    LocalDate.of(2050, APRIL, 5),
                    12346,
                    "NL12312442",
                    new Date(2023, 4, 5)
            );

            repository.saveAll(
                    List.of(henk, fred)
            );
        };
    }
}