package com.thanh.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // notify client of response body content type
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        // set the response status code
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // set up the response body
        Status unauthorized = new Status(HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage());
        // write the response body
        objectMapper.writeValue(response.getOutputStream(), unauthorized);
        // commit the response
        response.flushBuffer();
    }

    public record Status(int code, String message) {

    }
}