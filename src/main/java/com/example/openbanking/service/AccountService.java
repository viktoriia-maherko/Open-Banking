package com.example.openbanking.service;

import com.example.openbanking.dto.BalanceResponseDto;
import com.example.openbanking.dto.PaymentRequestDto;
import com.example.openbanking.dto.TransactionResponseDto;
import com.example.openbanking.model.Payment;

import java.util.List;

public interface AccountService {
    BalanceResponseDto getAccountBalance(String accountId);

    List<TransactionResponseDto> getAccountTransactionsByIban(String accountId);

    String initiatePayment(PaymentRequestDto requestDto);
}
