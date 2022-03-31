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
                    1L,
                    LocalDate.of(2050, APRIL, 5),
                    12345
            );
            NFCReader iris = new NFCReader(
                    2L,
                    LocalDate.of(2000, APRIL, 5),
                    12345
            );
            NFCReader fred = new NFCReader(
                    3L,
                    LocalDate.of(2025, APRIL, 5),
                    10100
            );

            repository.saveAll(
                    List.of(henk, iris, fred)
            );
        };
    }
}
