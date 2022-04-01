package com.trip.serviceterminal.bankcard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class BankCardConfig {

    @Bean
    CommandLineRunner commandLineRunner(BankCardRepository repository) {
        return args -> {
            BankCard rabo = new BankCard(
                    LocalDate.of(2025, APRIL, 1),
                    12345,
                    "NL99INGB1234567890");
            BankCard ing = new BankCard(
                    LocalDate.of(2005, APRIL, 1),
                    12345,
                    "NL33RABO1234567890");

            repository.saveAll(
                    List.of(rabo, ing));
        };
    }
}
