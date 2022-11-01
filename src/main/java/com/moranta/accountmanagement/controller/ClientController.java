package com.moranta.accountmanagement.controller;

import com.moranta.accountmanagement.service.ClientService;
import com.moranta.accountmanagement.dto.ClientInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ClientInfoDTO getClientInfo(@PathVariable Long id) {
        return clientService.renderClientInfo(id);
    }
}
