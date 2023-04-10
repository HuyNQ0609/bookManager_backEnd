package com.example.book_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String book_code;
    private String name;
    private String author;
    private Double price;

    public Book() {
    }

    public Book(String book_code, String name, String author, Double price) {
        this.book_code = book_code;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(Long id, String book_code, String name, String author, Double price) {
        this.id = id;
        this.book_code = book_code;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Book[id=%d, book_code='%s', name='%s', author='%s', price='%s']", id, book_code, name, author, price);
    }
}
