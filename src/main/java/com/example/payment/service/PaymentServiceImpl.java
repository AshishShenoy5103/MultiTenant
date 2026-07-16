package com.example.payment.service;

import com.example.payment.dto.CreatePaymentRequest;
import com.example.payment.dto.PaymentCreationResult;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentCreationResult createPayment(CreatePaymentRequest request) {
        return null;
    }

    @Override
    public PaymentCreationResult createFreshPayment(String tenantId, CreatePaymentRequest request) {
        return null;
    }
}
