package com.example.payment.context;

import java.time.Instant;

public record CacheEntry(TenantConfig config, Instant expiresAt) {
}
