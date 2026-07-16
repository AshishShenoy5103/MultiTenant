package com.example.payment.service;

import com.example.payment.dto.CreatePaymentRequest;
import com.example.payment.dto.PaymentCreationResult;

public interface PaymentService {
    PaymentCreationResult createPayment(CreatePaymentRequest request);
    PaymentCreationResult createFreshPayment(String tenantId, CreatePaymentRequest request);
}
