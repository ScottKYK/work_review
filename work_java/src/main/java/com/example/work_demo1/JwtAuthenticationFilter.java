package com.example.work_demo1;

import com.example.work_demo1.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwt;
        String account;

        jwt = authHeader.substring(7);
        Map<String, Object> map = tokenService.parseToken(jwt);
        account = (String) map.get("account");
        if(account == null){
            return;
        }
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken();

        filterChain.doFilter(request, response);
    }
}
