package com.example.openbanking.controller;

import com.example.openbanking.dto.BalanceResponseDto;
import com.example.openbanking.dto.TransactionResponseDto;
import com.example.openbanking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/accounts")
@Tag(name = "Accounts",
        description = "Endpoints for retrieving account balance and transactions")
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Get account balance",
            description = "Returns the current balance for a given IBAN account")
    @GetMapping(value = "/{accountId}/balance")
    public BalanceResponseDto getAccountBalanceByIban(@PathVariable String accountId) {
        return accountService.getAccountBalance(accountId);
    }

    @Operation(summary = "Get recent transactions",
            description = "Returns the 10 most recent transactions for the given IBAN account")
    @GetMapping(value = "/{accountId}/transactions")
    public List<TransactionResponseDto> getAccountTransactionsByIban(@PathVariable String accountId) {
        return accountService.getAccountTransactionsByIban(accountId);
    }
}
