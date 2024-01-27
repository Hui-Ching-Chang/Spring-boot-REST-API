package com.monica.travelersnotebook.traveler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TravelerConfig {
    @Bean
    CommandLineRunner commandLineRunner(TravelerRepository repository) {
        return args -> {
            Traveler monica = new Traveler(
                    "Monica",
                    "mon19940513@gmail.com",
                    LocalDate.of(1994, Month.MAY, 13)
            );

            Traveler alice = new Traveler(
                    "Alice",
                    "alice@gmail.com",
                    LocalDate.of(1999, Month.MAY, 5)
            );

            repository.saveAll(
                    List.of(monica, alice)
            );
        };
    }
}
