package com.example.bookstore.controller;

import com.example.bookstore.dto.BookForm;
import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CatalogService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manage")
public class ManageController {

    private final BookService bookService;
    private final CatalogService catalogService;

    public ManageController(BookService bookService, CatalogService catalogService) {
        this.bookService = bookService;
        this.catalogService = catalogService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "manage/books";
    }

    @GetMapping("/books/new")
    public String newBook(Model model) {
        model.addAttribute("bookForm", new BookForm());
        addLookups(model);
        return "manage/book-form";
    }

    @GetMapping("/books/{id}/edit")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));
        model.addAttribute("bookForm", BookForm.from(book));
        addLookups(model);
        return "manage/book-form";
    }

    @PostMapping("/books")
    public String saveBook(@Valid @ModelAttribute("bookForm") BookForm form,
                           BindingResult bindingResult, Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addLookups(model);
            return "manage/book-form";
        }
        Book book = toEntity(form);
        bookService.save(book);
        redirectAttributes.addFlashAttribute("success", "Book saved successfully.");
        return "redirect:/manage/books";
    }

    @PostMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Book deleted.");
        return "redirect:/manage/books";
    }

    private void addLookups(Model model) {
        model.addAttribute("authors", catalogService.findAllAuthors());
        model.addAttribute("categories", catalogService.findAllCategories());
    }

    private Book toEntity(BookForm form) {
        Author author = catalogService.findAllAuthors().stream()
                .filter(a -> a.getId().equals(form.getAuthorId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid author"));
        Category category = catalogService.findAllCategories().stream()
                .filter(c -> c.getId().equals(form.getCategoryId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid category"));

        Book book = new Book(form.getTitle(), author, category, form.getIsbn(),
                form.getPrice(), form.getStockQuantity(), form.getPublishedYear(),
                form.getDescription(), form.getCoverImageUrl());
        book.setId(form.getId());
        return book;
    }
}