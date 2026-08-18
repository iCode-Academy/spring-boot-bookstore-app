package com.example.bookstore.controller;

import com.example.bookstore.model.Cart;
import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final Cart cart;
    private final BookService bookService;

    public CartController(Cart cart, BookService bookService) {
        this.cart = cart;
        this.bookService = bookService;
    }

    @GetMapping
    public String view(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add/{bookId}")
    public String add(@PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        bookService.findById(bookId).ifPresent(book -> {
            int inCart = cart.getItems().containsKey(bookId)
                    ? cart.getItems().get(bookId).getQuantity() : 0;
            if (inCart < book.getStockQuantity()) {
                cart.addItem(book, 1);
            } else {
                redirectAttributes.addFlashAttribute("cartError",
                        "Only " + book.getStockQuantity() + " copies of \"" + book.getTitle() + "\" are in stock.");
            }
        });
        return "redirect:/books";
    }

    @PostMapping("/update/{bookId}")
    public String update(@PathVariable Long bookId, @RequestParam int quantity) {
        cart.getItems().values().stream()
                .filter(ci -> ci.getBook().getId().equals(bookId))
                .findFirst()
                .ifPresent(ci -> cart.updateQuantity(bookId,
                        Math.min(quantity, ci.getBook().getStockQuantity())));
        return "redirect:/cart";
    }

    @PostMapping("/remove/{bookId}")
    public String remove(@PathVariable Long bookId) {
        cart.removeItem(bookId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clear() {
        cart.clear();
        return "redirect:/cart";
    }
}