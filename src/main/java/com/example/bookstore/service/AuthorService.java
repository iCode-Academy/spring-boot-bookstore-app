package com.example.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.repository.AuthorRepository;

@Service
public class AuthorService {

	private final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

//	findAllAuthors -> List<Author>
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

//	findAuthorById (Long id) -> Author
	public Author findAuthorById(Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
	}

//	createAuthor(Author author) -> Author
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

//	updateAuthor(Long id, Author newAuthor) -> Author
	public Author updateAuthor(Long id, Author newAuthor) {

		Author foundAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));

		foundAuthor.setFirstName(newAuthor.getFirstName());

		foundAuthor.setLastName(newAuthor.getLastName());

		foundAuthor.setBio(newAuthor.getBio());

		return authorRepository.save(foundAuthor);
	}

//	deleteAuthor(Long id) -> void
	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
	}

}
