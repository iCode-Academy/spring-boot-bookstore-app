package com.example.bookstore.config;

import com.example.bookstore.model.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class WebConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Cart cart() {
        return new Cart();
    }
}