package com.moranta.accountmanagement.controller;

import com.moranta.accountmanagement.service.AccountService;
import com.moranta.accountmanagement.util.AccountRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public void createNewAccount(@RequestBody @Valid AccountRequest accountRequest, HttpServletResponse response) {
        accountService.createNewAccount(accountRequest);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
