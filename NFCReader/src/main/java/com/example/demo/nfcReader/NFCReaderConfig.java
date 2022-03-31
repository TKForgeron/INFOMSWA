package com.example.demo.nfcReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class NFCReaderConfig {

    @Bean
    CommandLineRunner commandLineRunner(NFCReaderRepository repository)  {
        return args -> {
            NFCReader henk = new NFCReader(
                    "Henk",
                    "henk.broodjeaap@gmail.com",
                    LocalDate.of(2000, APRIL, 5)
            );
            NFCReader bert = new NFCReader(
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
