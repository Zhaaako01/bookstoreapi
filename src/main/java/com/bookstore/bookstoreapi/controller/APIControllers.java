package com.bookstore.bookstoreapi.controller;

import com.bookstore.bookstoreapi.models.Book;
import com.bookstore.bookstoreapi.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIControllers {
    @Autowired
    private BookRepository bookRepo;

    @GetMapping(value = "api/books")
    public ResponseEntity<List<Book>> getAllBooks() {

        try {
            List<Book> bookList = bookRepo.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "api/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {

        try {
            Book book1 = bookRepo.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PutMapping(value = "api/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {

        try {
            Optional<Book> optionalBook = bookRepo.findById(id);
            if (optionalBook.isPresent()) {
                Book updatedBook = bookRepo.getReferenceById(id);
                updatedBook.setName(book.getName());
                updatedBook.setAuthor(book.getAuthor());
                updatedBook.setAmountOfPages(book.getAmountOfPages());
                updatedBook.setPrice(book.getPrice());
                Book book1 = bookRepo.save(updatedBook);
                return ResponseEntity.status(HttpStatus.OK).body(book1);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping(value = "api/books/{id}")
    public ResponseEntity<Optional<Book>> deleteBook(@PathVariable int id) {

        try {
            Optional<Book> deletedBook = bookRepo.findById(id);
            if (deletedBook.isPresent()) {
                bookRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(deletedBook);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "api/books/{id}")
    public ResponseEntity<Optional<Book>> getBookByID(@PathVariable int id) {

        try {
            Optional<Book> optionalBook = bookRepo.findById(id);
            if (optionalBook.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalBook);
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
