package com.example.work_demo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("Http://localhost:8080") // 前端跨域的URL
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE") // 前端跨域允許的請求方式
                .allowCredentials(true)
                .maxAge(3600);
    }


}
