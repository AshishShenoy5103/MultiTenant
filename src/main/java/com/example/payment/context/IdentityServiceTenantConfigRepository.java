package com.example.payment.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IdentityServiceTenantConfigRepository implements TenantConfigRepository{
    private final RestClient restClient;
    private final Duration cacheTtl;
    private final Clock clock;
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public IdentityServiceTenantConfigRepository(
        RestClient.Builder restClientBuilder,
        @Value("${identity.base-url}") String identityBaseUrl,
        @Value("${identity.cache-ttl-seconds:300}") long cacheTtlSeconds
    ) {
        this(restClientBuilder.baseUrl(identityBaseUrl).build(), Duration.ofSeconds(cacheTtlSeconds), Clock.systemUTC());
    }

    IdentityServiceTenantConfigRepository(RestClient restClient, Duration cacheTtl, Clock clock) {
        this.restClient = restClient;
        this.cacheTtl = cacheTtl;
        this.clock = clock;
    }

    @Override
    public TenantConfig findByTenantId(String tenantId) {
        CacheEntry existing = cache.get(tenantId);
        Instant now = Instant.now(clock);
        if(existing != null && now.isBefore(existing.expiresAt())) {
            return existing.config();
        }

        TenantConfig config = restClient.get()
                .uri("/internal/tenants/{tenantId}/stripe-config", tenantId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(TenantConfig.class);

        if(config == null || config.stripeApiKey() == null || config.stripeWebhookSecret() == null) {
            throw  new IllegalStateException("Identity service returned incomplete Stripe config for tenant" + tenantId);
        }

        cache.put(tenantId, new CacheEntry(config, now.plus(cacheTtl)));
        return config;
    }
}
