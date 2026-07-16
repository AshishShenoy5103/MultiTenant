package com.example.payment.stripe;

import java.math.BigDecimal;

public interface StripeGateway {
    String createCharge(String apiKey, String orderId, BigDecimal amount, String idempotencyKey);
}
