package com.example.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	// id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	Java firstName varchar(100) - database first_name
	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;

	// lastName varchar(100)
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	// bio length = 1000
	@Column(length = 1000)
	private String bio;

	public Author() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
