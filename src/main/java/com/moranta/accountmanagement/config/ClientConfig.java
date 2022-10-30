package com.moranta.accountmanagement.config;

import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {

        return args -> {
            Client john = new Client("John","ASE1234","Berthal","john.berthal@gmail.com");
            Client julie = new Client("Julie","ASE4321","Ramirez","julie.ramirez@gmail.com");
            Client frank = new Client("Frank","ASE5678","Castle","frank.castle@gmail.com");
            clientRepository.saveAll(List.of(john,julie,frank));
        };

    }
}
