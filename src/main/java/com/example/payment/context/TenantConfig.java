package com.example.payment.context;

public record TenantConfig (
        String tenantId,
        String stripeApiKey,
        String stripeWebhookSecret
) {
}
