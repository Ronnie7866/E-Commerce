package com.backend.ecommerce.security;

import com.backend.ecommerce.records.ErrorMessage;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        ErrorMessage a = new ErrorMessage(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), null, "Access Denied: " + authException.getMessage(), request.getRequestURI());
        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(a);
        PrintWriter writer = response.getWriter();
        writer.println(json);
    }
}