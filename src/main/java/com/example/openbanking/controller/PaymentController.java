package com.example.openbanking.controller;

import com.example.openbanking.dto.PaymentRequestDto;
import com.example.openbanking.model.Payment;
import com.example.openbanking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payments")
@Tag(name = "Payments",
        description = "Endpoints for initiating payments")
public class PaymentController {
    private final AccountService accountService;
    @Operation(summary = "Initiate payment",
            description = "Initiates a payment from one IBAN to another with specified amount and currency")
    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody PaymentRequestDto requestDto) {
        return accountService.initiatePayment(requestDto);
    }
}

