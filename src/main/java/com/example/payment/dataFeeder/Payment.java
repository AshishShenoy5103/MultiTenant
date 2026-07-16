package com.example.payment.dataFeeder;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_payments_tenant_idempotency", columnList = "tenant_id, idempotency_key", unique = true)
        }
)
public class Payment {
    @Id
    @Column(name = "payment_id", nullable = false, updatable = false, length = 36)
    private String paymentId;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    private String stripeChargeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    public Payment() {}

    public Payment(String tenantId, String orderId, BigDecimal amount, String idempotencyKey, String stripeChargeId, PaymentStatus paymentStatus) {
        this.tenantId = tenantId;
        this.orderId = orderId;
        this.amount = amount;
        this.idempotencyKey = idempotencyKey;
        this.stripeChargeId = stripeChargeId;
        this.paymentStatus = paymentStatus;
    }

    @PrePersist
    public void generateId() {
        if(this.paymentId == null) {
            this.paymentId = UUID.randomUUID().toString();
        }
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public String getStripeChargeId() {
        return stripeChargeId;
    }

    public void setStripeChargeId(String stripeChargeId) {
        this.stripeChargeId = stripeChargeId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
