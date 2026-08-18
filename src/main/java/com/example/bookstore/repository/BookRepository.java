package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByOrderByTitleAsc();

    List<Book> findByCategoryId(Long categoryId);

    @Query("""
            SELECT b FROM Book b
            WHERE (:categoryId IS NULL OR b.category.id = :categoryId)
              AND (:keyword IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
            ORDER BY b.title ASC
            """)
    List<Book> search(@Param("categoryId") Long categoryId, @Param("keyword") String keyword);
}