package com.example.payment.security;

import com.example.payment.dataFeeder.DataFeeder;
import com.example.payment.dataFeeder.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final DataFeeder dataFeeder;

    public CustomUserDetailsService(DataFeeder dataFeeder) {
        this.dataFeeder = dataFeeder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dataFeeder.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        /*
        * The UserDetails has collection of GrantedAuthorites. GrantedAuthorities is an interface.
        * So here SimpleGrantedAuthority is implementation of that interface. And we are transforming
        * String to SimpleGrantedAuthority object so that spring can read from it.
        * */

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new CustomUserDetails(
                user.getEmail(),
                user.getPasswordHash(),
                user.getTenantId(),
                authorities
        );
    }
}
