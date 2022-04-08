package com.trip.subscriptionservice.subscription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubscriptionConfig {

        @Bean
        CommandLineRunner commandLineRunner(SubscriptionRepository repository) {
                return args -> {
                        // private Long id;
                        // private String tycoon;
                        // private Integer discountPercentage;
                        // private String description;

                        Subscription offpeakNS = new Subscription(
                                        1L,
                                        3,
                                        40,
                                        "Valid for routes of Nederlandse Sportvereniging tycoon, 40% off outside of peak hours");
                        Subscription offpeakVerbindding = new Subscription(
                                        2L,
                                        4,
                                        40,
                                        "Valid for routes of Verbindding tycoon, 40% off outside of peak hours");
                        Subscription fullNS = new Subscription(
                                        3L,
                                        3,
                                        100,
                                        "Valid for routes of Nederlandse Sportvereniging tycoon, free travelling 24/7");

                        Subscription fullThais = new Subscription(
                                        4L,
                                        1,
                                        100,
                                        "Valid for routes of Thais tycoon, free travelling 24/7");

                        repository.saveAll(
                                        List.of(offpeakNS, offpeakVerbindding, fullNS, fullThais));
                };
        }
}
