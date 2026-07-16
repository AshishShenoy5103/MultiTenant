package com.example.payment.dto;

import com.example.payment.dataFeeder.Payment;

public record PaymentCreationResult (
        Payment payment,
        boolean duplicate
) {
}
