package com.example.payment.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String message;
        if (authException instanceof BadCredentialsException) {
            // Wrong email or wrong password at login - deliberately vague,
            // same message for both cases so we don't leak which emails exist.
            message = "Invalid credentials";
        } else if (authException instanceof InsufficientAuthenticationException) {
            // No token at all was presented on a protected endpoint.
            message = "Authentication required";
        } else {
            // Covers expired/malformed/tampered JWTs bubbling up from TenantContextFilter.
            message = "Invalid or expired token";
        }

        response.getWriter().write("""
            {
              "error": "Unauthorized",
              "message": "%s"
            }
            """.formatted(message));
    }
}
