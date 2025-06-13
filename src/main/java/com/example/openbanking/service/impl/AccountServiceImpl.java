package com.example.openbanking.service.impl;

import com.example.openbanking.dto.BalanceResponseDto;
import com.example.openbanking.dto.PaymentRequestDto;
import com.example.openbanking.dto.TransactionResponseDto;
import com.example.openbanking.model.Payment;
import com.example.openbanking.repository.PaymentRepository;
import com.example.openbanking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final WebClient webClient;
    private final PaymentRepository paymentRepository;
    @Override
    public BalanceResponseDto getAccountBalance(String accountId) {
        return webClient.get()
                .uri("/mock/accounts/{accountId}/balance", accountId)
                .retrieve()
                .bodyToMono(BalanceResponseDto.class)
                .block();
    }

    @Override
    public List<TransactionResponseDto> getAccountTransactionsByIban(String accountId) {
        return webClient.get()
                .uri("/mock/accounts/{accountId}/transactions", accountId)
                .retrieve()
                .bodyToFlux(TransactionResponseDto.class)
                .collectList()
                .block();
    }

    @Override
    public String initiatePayment(PaymentRequestDto requestDto) {
        BalanceResponseDto accountBalance = getAccountBalance(requestDto.fromIban());
        if (accountBalance.amount().compareTo(requestDto.amount()) < 0) {
             return "Insufficient funds";
        }

        Payment payment = new Payment();
        payment.setAmount(requestDto.amount());
        payment.setCurrency(requestDto.currency());
        payment.setDescription(requestDto.description());
        payment.setFromIban(requestDto.fromIban());
        payment.setToIban(requestDto.toIban());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus(Payment.Status.PENDING);

        paymentRepository.save(payment);
        String result = webClient.post()
                .uri("/mock/accounts/payments")
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        payment.setStatus("SUCCESS".equalsIgnoreCase(result)
                ? Payment.Status.SUCCESS : Payment.Status.FAILED);
        paymentRepository.save(payment);
        return result;
    }
}
