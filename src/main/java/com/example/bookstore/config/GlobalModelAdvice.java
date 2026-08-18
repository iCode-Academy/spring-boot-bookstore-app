package com.example.bookstore.config;

import com.example.bookstore.model.Cart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {

    private final Cart cart;

    public GlobalModelAdvice(Cart cart) {
        this.cart = cart;
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return cart;
    }
}