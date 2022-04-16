package com.trip.nfcreaderapp.nfcReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class NFCReaderConfig {

        @Bean
        CommandLineRunner commandLineRunner(NFCReaderRepository repository) {
                return args -> {
                        BankCard henk = new BankCard(
                                        1L,
                                        LocalDate.of(2025, APRIL, 5),
                                        12345);
                        BankCard iris = new BankCard(
                                        2L,
                                        LocalDate.of(2000, APRIL, 5),
                                        12346);
                        BankCard fred = new BankCard(
                                        3L,
                                        LocalDate.of(2025, APRIL, 5),
                                        10100);

                        repository.saveAll(
                                        List.of(henk, iris, fred));
                };
        }
}
