package com.example.bookstore.dto;

import com.example.bookstore.entity.Book;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BookForm {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Author is required")
    private Long authorId;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    private Integer publishedYear;

    private String description;

    private String coverImageUrl;

    public static BookForm from(Book book) {
        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setTitle(book.getTitle());
        form.setAuthorId(book.getAuthor().getId());
        form.setCategoryId(book.getCategory().getId());
        form.setIsbn(book.getIsbn());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        form.setPublishedYear(book.getPublishedYear());
        form.setDescription(book.getDescription());
        form.setCoverImageUrl(book.getCoverImageUrl());
        return form;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}