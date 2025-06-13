package com.example.openbanking.controller;

import com.example.openbanking.dto.BalanceResponseDto;
import com.example.openbanking.dto.TransactionResponseDto;
import com.example.openbanking.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void shouldReturnBalanceForAccount() throws Exception {
        String accountId = "DE1234567890";
        BalanceResponseDto mockResponse = new BalanceResponseDto(accountId,
                new BigDecimal("1000.00"), "EUR");
        when(accountService.getAccountBalance(accountId)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/accounts/" + accountId + "/balance")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.iban").value(accountId))
                .andExpect(jsonPath("$.amount").value(1000.00))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void getAccountTransactionsByIban() throws Exception {
        String accountId = "DE1234567890";

        List<TransactionResponseDto> mockTransactions = List.of(new TransactionResponseDto("tx1", accountId, "Aroma Coffee", new BigDecimal("4.50"), "EUR", LocalDate.now().minusDays(1)),
                new TransactionResponseDto("tx2", accountId, "ATB Supermarket", new BigDecimal("27.80"), "EUR", LocalDate.now().minusDays(2)),
                new TransactionResponseDto("tx3", accountId, "Salary", new BigDecimal("1250.00"), "EUR", LocalDate.now().minusDays(3)),
                new TransactionResponseDto("tx4", accountId, "Internet Provider", new BigDecimal("10.00"), "EUR", LocalDate.now().minusDays(4)),
                new TransactionResponseDto("tx5", accountId, "Public Transport", new BigDecimal("2.00"), "EUR", LocalDate.now().minusDays(5)),
                new TransactionResponseDto("tx6", accountId, "Cashback from Bank", new BigDecimal("3.25"), "EUR", LocalDate.now().minusDays(6)),
                new TransactionResponseDto("tx7", accountId, "Silpo Grocery", new BigDecimal("45.10"), "EUR", LocalDate.now().minusDays(7)),
                new TransactionResponseDto("tx8", accountId, "Pharmacy", new BigDecimal("12.90"), "EUR", LocalDate.now().minusDays(8)),
                new TransactionResponseDto("tx9", accountId, "Tax Refund", new BigDecimal("20.00"), "EUR", LocalDate.now().minusDays(9)),
                new TransactionResponseDto("tx10", accountId, "Utility Bills", new BigDecimal("88.40"), "EUR", LocalDate.now().minusDays(10)));

        when(accountService.getAccountTransactionsByIban(accountId)).thenReturn(mockTransactions);

        mockMvc.perform(get("/api/accounts/" + accountId + "/transactions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10))
                .andExpect(jsonPath("$[0].description").value("Aroma Coffee"))
                .andExpect(jsonPath("$[1].amount").value(27.80));
    }
}