package com.example.payment.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final String tenantId;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String password, String tenantId, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.tenantId = tenantId;
        this.authorities = authorities;
    }

    public String getTenantId() {
        return tenantId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
