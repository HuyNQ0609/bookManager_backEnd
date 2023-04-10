package com.example.book_manager.service.book;

import com.example.book_manager.model.Book;
import com.example.book_manager.service.IGeneralService;

import java.util.List;

public interface IBookService extends IGeneralService<Book> {
    List<Book> findBookByPrice(Double minPrice, Double maxPrice);
    List<Book> findBookByNameAndAuthor(String name, String author);
    Double getTotalPriceAllBooks();
}
