package com.employee.management.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {
        
        String requestURI = request.getRequestURI();
        
        // Allow access to login and registration pages without authentication
        if (requestURI.contains("/login") || 
            requestURI.contains("/registration") || 
            requestURI.equals("/") ||
            requestURI.startsWith("/css/") ||
            requestURI.startsWith("/js/") ||
            requestURI.startsWith("/resources/") ||
            requestURI.startsWith("/images/")) {
            return true;
        }
        
        HttpSession session = request.getSession(false);
        
        // Check if user is logged in
        if (session != null && session.getAttribute("user_email") != null) {
            return true;
        }
        
        // Redirect to login if not authenticated
        response.sendRedirect("/login");
        return false;
    }
}