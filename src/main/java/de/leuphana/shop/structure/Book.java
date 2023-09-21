package de.leuphana.shop.structure;

import jakarta.persistence.Entity;

@Entity
public class Book extends Article {

	private String author;
	private de.leuphana.shop.structure.BookCategory bookCategory;

	public Book() {
		super();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public de.leuphana.shop.structure.BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

}