package de.leuphana.article.structure.database.entity;

import jakarta.persistence.Entity;

@Entity
public class BookEntity extends ArticleEntity {
    private String author;
    private String bookCategory;

    public BookEntity() {
    }

    public BookEntity(String manufacturer, String name, float price, String author, String bookCategory) {
        super(manufacturer, name, price);
        this.author = author;
        this.bookCategory = bookCategory;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
}
