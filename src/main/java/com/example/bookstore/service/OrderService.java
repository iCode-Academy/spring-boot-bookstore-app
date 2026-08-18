package com.example.bookstore.service;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.OrderStatus;
import com.example.bookstore.model.Cart;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final Cart cart;
    private final BookService bookService;

    public OrderService(OrderRepository orderRepository, Cart cart, BookService bookService) {
        this.orderRepository = orderRepository;
        this.cart = cart;
        this.bookService = bookService;
    }

    public Order placeOrder(String customerName, String customerEmail) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Order order = new Order(customerName, customerEmail, LocalDateTime.now(),
                cart.getTotal(), OrderStatus.PLACED);

        cart.getItems().values().forEach(ci ->
                order.addItem(new OrderItem(ci.getBook(), ci.getQuantity(), ci.getBook().getPrice())));

        order = orderRepository.save(order);

        cart.getItems().values().forEach(ci ->
                bookService.decrementStock(ci.getBook().getId(), ci.getQuantity()));

        cart.clear();
        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    @Transactional(readOnly = true)
    public Order findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}