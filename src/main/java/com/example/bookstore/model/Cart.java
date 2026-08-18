package com.example.bookstore.model;

import com.example.bookstore.entity.Book;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private final Map<Long, CartItem> items = new LinkedHashMap<>();

    public void addItem(Book book, int quantity) {
        CartItem item = items.get(book.getId());
        if (item == null) {
            items.put(book.getId(), new CartItem(book, quantity));
        } else {
            item.increment(quantity);
        }
    }

    public void updateQuantity(Long bookId, int quantity) {
        CartItem item = items.get(bookId);
        if (item != null) {
            if (quantity <= 0) {
                items.remove(bookId);
            } else {
                item.setQuantity(quantity);
            }
        }
    }

    public void removeItem(Long bookId) {
        items.remove(bookId);
    }

    public void clear() {
        items.clear();
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}