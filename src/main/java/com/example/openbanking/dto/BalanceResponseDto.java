package com.example.openbanking.dto;

import java.math.BigDecimal;

public record BalanceResponseDto(String iban,
                                 BigDecimal amount,
                                 String currency) {
}
