package com.example.openbanking.external;

import com.example.openbanking.dto.BalanceResponseDto;
import com.example.openbanking.dto.PaymentRequestDto;
import com.example.openbanking.dto.TransactionResponseDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mock/accounts")
public class ExternalBankMockController {
    @GetMapping("/{accountId}/balance")
    public BalanceResponseDto getMockBalance(@PathVariable String accountId) {
        return new BalanceResponseDto(accountId, new BigDecimal("111.11"), "USD");
    }

    @GetMapping("/{accountId}/transactions")
    public List<TransactionResponseDto> getMockTransactions(@PathVariable String accountId) {
        return List.of(new TransactionResponseDto("tx1", accountId, "Aroma Coffee", new BigDecimal("-4.50"), "EUR", LocalDate.now().minusDays(1)),
                new TransactionResponseDto("tx2", accountId, "ATB Supermarket", new BigDecimal("-27.80"), "EUR", LocalDate.now().minusDays(2)),
                new TransactionResponseDto("tx3", accountId, "Salary", new BigDecimal("1250.00"), "EUR", LocalDate.now().minusDays(3)),
                new TransactionResponseDto("tx4", accountId, "Internet Provider", new BigDecimal("-10.00"), "EUR", LocalDate.now().minusDays(4)),
                new TransactionResponseDto("tx5", accountId, "Public Transport", new BigDecimal("-2.00"), "EUR", LocalDate.now().minusDays(5)),
                new TransactionResponseDto("tx6", accountId, "Cashback from Bank", new BigDecimal("3.25"), "EUR", LocalDate.now().minusDays(6)),
                new TransactionResponseDto("tx7", accountId, "Silpo Grocery", new BigDecimal("-45.10"), "EUR", LocalDate.now().minusDays(7)),
                new TransactionResponseDto("tx8", accountId, "Pharmacy", new BigDecimal("-12.90"), "EUR", LocalDate.now().minusDays(8)),
                new TransactionResponseDto("tx9", accountId, "Tax Refund", new BigDecimal("20.00"), "EUR", LocalDate.now().minusDays(9)),
                new TransactionResponseDto("tx10", accountId, "Utility Bills", new BigDecimal("-88.40"), "EUR", LocalDate.now().minusDays(10)));
    }

    @PostMapping("/payments")
    public String mockPayment(@RequestBody PaymentRequestDto requestDto) {
        return "SUCCESS";
    }
}
