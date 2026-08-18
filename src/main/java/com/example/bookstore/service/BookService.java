package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    @Transactional(readOnly = true)
    public List<Book> search(Long categoryId, String keyword) {
        String normalized = (keyword == null || keyword.isBlank()) ? null : keyword.trim();
        return bookRepository.search(categoryId, normalized);
    }

    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public void decrementStock(Long bookId, int quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + bookId));
        book.setStockQuantity(book.getStockQuantity() - quantity);
    }
}