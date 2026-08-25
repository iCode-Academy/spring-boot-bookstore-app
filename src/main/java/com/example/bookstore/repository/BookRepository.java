package com.example.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	boolean existsByIsbn(String isbn);

	boolean existsByIsbnAndIdNot(String isbn, Long id);

 /**
  * 
  * 	Book 1
		ISBN = ABC123
		
		Book 2
		ISBN = XYZ999
		
//		book 1 => ISBN = ABC123
		
  		* SELECT EXISTS (
		    SELECT 1
		    FROM books
		    WHERE isbn = 'ABC123'
		      AND id <> 1
		);
  */
}
