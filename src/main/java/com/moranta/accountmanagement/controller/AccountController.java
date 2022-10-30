package com.moranta.accountmanagement.controller;

import com.moranta.accountmanagement.service.AccountService;
import com.moranta.accountmanagement.dto.AccountRequestDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public void createNewAccount(@RequestBody AccountRequestDTO accountRequestDTO, HttpServletResponse response) {
        accountService.createNewAccount(accountRequestDTO);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
