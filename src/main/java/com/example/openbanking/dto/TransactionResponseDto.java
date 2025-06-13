package com.example.openbanking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseDto(String transactionId,
                                     String iban,
                                     String description,
                                     BigDecimal amount,
                                     String currency,
                                     LocalDate date) {
}
