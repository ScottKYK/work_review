package com.example.work_demo1.config;

import com.example.work_demo1.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize //對所有訪問HTTP端點的HttpServletRequest進行限制
                        .requestMatchers("/test/*").permitAll()//指定上述的路徑，允許所有用戶訪問，不需身份驗證
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/test2").permitAll()
                        .requestMatchers("/parse_token").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()   //其他尚路徑都需身份驗證
                );

        return http.build();
    }


}