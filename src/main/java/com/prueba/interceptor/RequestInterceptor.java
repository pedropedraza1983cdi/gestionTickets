package com.prueba.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("➡ [INTERCEPTOR] Petición: " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("➡ Hora: " + LocalDateTime.now());
        return true; // Si devuelve false, bloquea la petición
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("✅ [INTERCEPTOR] Respuesta enviada con código: " + response.getStatus());
    }
}
