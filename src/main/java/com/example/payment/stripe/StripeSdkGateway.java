package com.example.payment.stripe;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StripeSdkGateway implements StripeGateway{
    @Override
    public String createCharge(String apiKey, String orderId, BigDecimal amount, String idempotencyKey) {
        StripeClient client = new StripeClient(apiKey);

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount.movePointRight(2).longValueExact())
                .setCurrency("usd")
                .putMetadata("orderId", orderId)
                .setConfirm(true)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        RequestOptions requestOptions = RequestOptions.builder()
                .setIdempotencyKey(idempotencyKey)
                .build();

        try {
            PaymentIntent paymentIntent = client.paymentIntents().create(params, requestOptions);
            return paymentIntent.getId();
        } catch (StripeException ex) {
            throw new IllegalStateException("Stripe charge creation failed", ex);
        }
    }
}
