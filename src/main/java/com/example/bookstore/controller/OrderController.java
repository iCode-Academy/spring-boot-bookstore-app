package com.example.bookstore.controller;

import com.example.bookstore.dto.CheckoutForm;
import com.example.bookstore.entity.Order;
import com.example.bookstore.model.Cart;
import com.example.bookstore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final Cart cart;

    public OrderController(OrderService orderService, Cart cart) {
        this.orderService = orderService;
        this.cart = cart;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@Valid @ModelAttribute("checkoutForm") CheckoutForm form,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cart", cart);
            return "checkout";
        }
        if (cart.isEmpty()) {
            return "redirect:/books";
        }
        Order order = orderService.placeOrder(form.getCustomerName(), form.getCustomerEmail());
        return "redirect:/orders/" + order.getId();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "orders";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findOrder(id));
        return "order-detail";
    }
}