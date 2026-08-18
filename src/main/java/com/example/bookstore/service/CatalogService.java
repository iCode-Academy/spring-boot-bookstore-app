package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CatalogService {

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public CatalogService(AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}