package com.moranta.accountmanagement.controller;

import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName(value = "/api/v1/accounts - Incorrect post body throws 400")
    void createNewAccountIncorrectJson() throws Exception {
        // when
        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": \"1\", \"amount\": \"100\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "/api/v1/accounts - Incorrect post body throws 400")
    void createNewAccount() throws Exception {
        // given
        Client client = createClient();

        // when
        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": \"1\", \"initialCredit\": \"100\"}"))
                .andExpect(status().isCreated());
    }

    Client createClient() {
        Client client = new Client("Patrick", "1","Dubosc", "patrick@gmail.com");
        clientRepository.save(client);
        return client;
    }
}
