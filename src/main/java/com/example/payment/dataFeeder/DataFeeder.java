package com.example.payment.dataFeeder;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DataFeeder {
    private final PasswordEncoder passwordEncoder;
    private final Map<String, User> users = new HashMap<>();


    public DataFeeder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seed() {
        users.put("ravi@pottery.com", new User("ravi@pottery.com", passwordEncoder.encode("password12345"), List.of("ROLE_TENANT_ADMIN"), "tenant-ravi-pottery-001"));
        users.put("priya@pottery.com", new User("priya@pottery.com", passwordEncoder.encode("password12345"), List.of("ROLE_USER"), "tenant-ravi-pottery-001"));
        users.put("support@shopkart.com", new User("support@shopkart.com", passwordEncoder.encode("password12345"), List.of("ROLE_USER"), "tenant-abc-123"));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }
}
