package com.example.bookstore.model;

import com.example.bookstore.entity.Book;

import java.math.BigDecimal;

public class CartItem {

    private final Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increment(int amount) {
        this.quantity += amount;
    }
}