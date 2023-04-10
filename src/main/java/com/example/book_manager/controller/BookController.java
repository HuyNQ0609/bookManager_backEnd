package com.example.book_manager.controller;

import com.example.book_manager.model.Book;
import com.example.book_manager.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private IBookService bookService;
    @GetMapping("")
    public ResponseEntity<Iterable<Book>> findAllBook() {
        List<Book> books = (List<Book>) bookService.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(bookOptional.get().getId());
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.remove(id);
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Book> viewBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        return bookOptional.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPrice() {
        Double totalPrice = bookService.getTotalPriceAllBooks();
        return ResponseEntity.ok(totalPrice);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(
            @RequestParam(value = "name") Optional<String> name,
            @RequestParam(value = "author") Optional<String> author,
            @RequestParam(value = "minPrice", required = false) Optional<Double> minPrice,
            @RequestParam(value = "maxPrice", required = false) Optional<Double> maxPrice) {
        List<Book> books;
        if (name.isPresent() & author.isPresent()) {
            books = bookService.findBookByNameAndAuthor(
                    name.orElse("").trim().toLowerCase(),
                    author.orElse("").trim().toLowerCase());
        } else if (minPrice.isPresent() || maxPrice.isPresent()) {
            books = bookService.findBookByPrice(
                    minPrice.orElse((double) 0),
                    maxPrice.orElse((double) 0));
        } else {
            books = (List<Book>) bookService.findAll();
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
