package com.example.payment.dataFeeder;

import java.time.Instant;
import java.util.List;

public class User {
    private String email;
    private String passwordHash;   // BCrypt hash - never store plaintext password
    private List<String> roles;    // e.g. ["ROLE_USER"] or ["ROLE_TENANT_ADMIN"] - same as your travel app
    private String tenantId;       // NEW - which tenant (seller) this user belongs to
    private Instant createdAt;

    public User(String email, String passwordHash, List<String> roles, String tenantId) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
        this.tenantId = tenantId;
        this.createdAt = Instant.now();
    }

    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public List<String> getRoles() { return roles; }
    public String getTenantId() { return tenantId; }
    public Instant getCreatedAt() { return createdAt; }
}
