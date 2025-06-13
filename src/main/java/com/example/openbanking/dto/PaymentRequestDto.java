package com.example.openbanking.dto;

import java.math.BigDecimal;

public record PaymentRequestDto(String fromIban,
                                String toIban,
                                BigDecimal amount,
                                String currency,
                                String description) {
}
