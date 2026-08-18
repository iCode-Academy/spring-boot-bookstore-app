package com.example.bookstore.config;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public DataSeeder(AuthorRepository authorRepository,
                      CategoryRepository categoryRepository,
                      BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            log.info("Books already present, skipping seed data.");
            return;
        }

        Category fiction = categoryRepository.save(new Category("Fiction"));
        Category fantasy = categoryRepository.save(new Category("Fantasy"));
        Category science = categoryRepository.save(new Category("Science"));
        Category technology = categoryRepository.save(new Category("Technology"));
        Category nonFiction = categoryRepository.save(new Category("Non-Fiction"));

        Author fitzgerald = authorRepository.save(new Author("F. Scott Fitzgerald",
                "American novelist known for capturing the Jazz Age."));
        Author lee = authorRepository.save(new Author("Harper Lee",
                "American novelist, author of To Kill a Mockingbird."));
        Author orwell = authorRepository.save(new Author("George Orwell",
                "English novelist and essayist famous for dystopian fiction."));
        Author harari = authorRepository.save(new Author("Yuval Noah Harari",
                "Israeli historian and bestselling author of Sapiens."));
        Author hawking = authorRepository.save(new Author("Stephen Hawking",
                "Theoretical physicist and author of A Brief History of Time."));
        Author dawkins = authorRepository.save(new Author("Richard Dawkins",
                "Evolutionary biologist and author of The Selfish Gene."));
        Author martin = authorRepository.save(new Author("Robert C. Martin",
                "Software engineer and author of Clean Code, known as Uncle Bob."));
        Author thomas = authorRepository.save(new Author("David Thomas & Andrew Hunt",
                "Co-authors of The Pragmatic Programmer."));
        Author austen = authorRepository.save(new Author("Jane Austen",
                "English novelist of the Romantic era."));
        Author tolkien = authorRepository.save(new Author("J.R.R. Tolkien",
                "English writer, poet, and author of The Lord of the Rings."));

        bookRepository.save(new Book("The Great Gatsby", fitzgerald, fiction, "9780743273565",
                new BigDecimal("12.99"), 25, 1925,
                "A portrait of the Jazz Age in all of its decadence and excess.",
                cover("9780743273565")));
        bookRepository.save(new Book("To Kill a Mockingbird", lee, fiction, "9780061120084",
                new BigDecimal("11.99"), 20, 1960,
                "The unforgettable novel of a childhood in a sleepy Southern town.",
                cover("9780061120084")));
        bookRepository.save(new Book("1984", orwell, fiction, "9780451524935",
                new BigDecimal("9.99"), 30, 1949,
                "Winston Smith rewrites the past in a world of total surveillance.",
                cover("9780451524935")));
        bookRepository.save(new Book("Sapiens: A Brief History of Humankind", harari, nonFiction,
                "9780062316097", new BigDecimal("19.99"), 15, 2015,
                "The story of how a remarkable ape came to dominate the planet.",
                cover("9780062316097")));
        bookRepository.save(new Book("A Brief History of Time", hawking, science, "9780553380163",
                new BigDecimal("15.99"), 18, 1998,
                "From the big bang to black holes, in plain English.",
                cover("9780553380163")));
        bookRepository.save(new Book("The Selfish Gene", dawkins, science, "9780198788607",
                new BigDecimal("14.99"), 12, 2016,
                "The classic introduction to evolution by natural selection.",
                cover("9780198788607")));
        bookRepository.save(new Book("Clean Code", martin, technology, "9780132350884",
                new BigDecimal("42.99"), 22, 2008,
                "A handbook of agile software craftsmanship.",
                cover("9780132350884")));
        bookRepository.save(new Book("The Pragmatic Programmer", thomas, technology, "9780135957059",
                new BigDecimal("45.99"), 10, 2019,
                "Your journey to mastery — timeless lessons for software developers.",
                cover("9780135957059")));
        bookRepository.save(new Book("Pride and Prejudice", austen, fiction, "9780141439518",
                new BigDecimal("10.99"), 28, 1813,
                "Elizabeth Bennet's wit and the proud Mr. Darcy collide.",
                cover("9780141439518")));
        bookRepository.save(new Book("The Hobbit", tolkien, fantasy, "9780547928227",
                new BigDecimal("13.99"), 26, 1937,
                "Bilbo Baggins is swept into an unexpected journey.",
                cover("9780547928227")));

        log.info("Seeded {} books.", bookRepository.count());
    }

    private String cover(String isbn) {
        return "https://covers.openlibrary.org/b/isbn/" + isbn + "-M.jpg";
    }
}