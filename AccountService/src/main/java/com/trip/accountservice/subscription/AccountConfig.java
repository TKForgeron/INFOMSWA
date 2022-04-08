package com.trip.accountservice.subscription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class AccountConfig {
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository) {
        return args -> {
            Account fred = new Account(
                    1L,
                    LocalDate.of(2050, APRIL, 5),
                    12346,
                    "NL99RABO1234567890",
                    Arrays.asList(1, 2),
                    new Date());
            Account henk = new Account(
                    2L,
                    LocalDate.of(2025, APRIL, 1),
                    12345,
                    "NL99INGB1234567890",
                    Arrays.asList(2),
                    new Date());
            Account truus = new Account(
                    3L,
                    LocalDate.of(2022, APRIL, 30),
                    99999,
                    "NL00BUNQ1234567890",
                    Arrays.asList(),
                    new Date());

            repository.saveAll(
                    List.of(fred, henk, truus));
        };
    }
}