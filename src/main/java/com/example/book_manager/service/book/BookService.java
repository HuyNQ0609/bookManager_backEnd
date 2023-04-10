package com.example.book_manager.service.book;

import com.example.book_manager.model.Book;
import com.example.book_manager.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBookByPrice(Double minPrice, Double maxPrice) {
        return bookRepository.findBookByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Book> findBookByNameAndAuthor(String name, String author) {
        return bookRepository.findBookByNameAndAuthor(name, author);
    }

    @Override
    public Double getTotalPriceAllBooks() {
        List<Book> books = bookRepository.findAll();
        double totalPrice = 0;
        for (Book book : books) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }
}
