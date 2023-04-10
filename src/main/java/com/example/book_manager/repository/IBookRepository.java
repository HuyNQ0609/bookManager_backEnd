package com.example.book_manager.repository;

import com.example.book_manager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByPrice(Double minPrice, Double maxPrice);
    List<Book> findBookByNameAndAuthor(String name, String author);
}
