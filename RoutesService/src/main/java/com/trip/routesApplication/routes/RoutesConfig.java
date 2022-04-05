package com.trip.routesApplication.routes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoutesConfig {

        @Bean
        CommandLineRunner commandLineRunner(RoutesRepository repository) {
                return args -> {
                        Route Purple = new Route(
                                        3L,
                                        "Apeldoorn",
                                        "Barneveld",
                                        5f,
                                        "Verbindding");
                        Route Blue_BE = new Route(
                                        3L,
                                        "Barneveld",
                                        "Ede",
                                        5f,
                                        "Nederlandse Sportvereniging");
                        Route Blue_EU = new Route(
                                        2L,
                                        "Ede",
                                        "Utrecht",
                                        6f,
                                        "Nederlandse Sportvereniging");
                        Route Green = new Route(
                                        1L,
                                        "Utrecht",
                                        "Amersfoort",
                                        3f,
                                        "Blauwnet-niet");
                        Route Red = new Route(
                                        3L,
                                        "Amersfoort",
                                        "Barneveld",
                                        2f,
                                        "Thais");
                        Route Blue_BU = new Route(
                                        3L,
                                        "Barneveld",
                                        "Utrecht",
                                        7f,
                                        "Nederlandse Sportvereniging");

                        repository.saveAll(
                                        List.of(Purple, Blue_BE, Blue_EU, Green, Red, Blue_BU));
                };
        }
}
