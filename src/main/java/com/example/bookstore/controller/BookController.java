package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final CatalogService catalogService;

    public BookController(BookService bookService, CatalogService catalogService) {
        this.bookService = bookService;
        this.catalogService = catalogService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) Long category,
                       @RequestParam(required = false) String q,
                       Model model) {
        List<Book> books = bookService.search(category, q);
        model.addAttribute("books", books);
        model.addAttribute("categories", catalogService.findAllCategories());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("keyword", q);
        return "index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));
        model.addAttribute("book", book);
        return "book-detail";
    }
}