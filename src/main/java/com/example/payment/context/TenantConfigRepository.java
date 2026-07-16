package com.example.payment.context;

public interface TenantConfigRepository {
    TenantConfig findByTenantId(String tenantId);
}
