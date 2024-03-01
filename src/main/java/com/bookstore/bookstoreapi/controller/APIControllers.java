package com.bookstore.bookstoreapi.controller;

import com.bookstore.bookstoreapi.models.Book;
import com.bookstore.bookstoreapi.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIControllers {
    @Autowired
    private BookRepository bookRepo;

    @GetMapping(value = "api/books")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @PostMapping(value = "api/save")
    public String saveBook(@RequestBody Book book) {
        bookRepo.save(book);
        return "Book saved to the DataBase";
    }

    @PutMapping(value = "api/update/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        Book updatedBook = bookRepo.getReferenceById(id);
        updatedBook.setName(book.getName());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setAmountOfPages(book.getAmountOfPages());
        updatedBook.setPrice(book.getPrice());
        bookRepo.save(updatedBook);
        return "Book updated";
    }

    @DeleteMapping(value = "api/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        Book deletedBook = bookRepo.getReferenceById(id);
        bookRepo.delete(deletedBook);
        return "Book by the id: " + id + " deleted";
    }

    @GetMapping(value = "api/books/{id}")
    public Book getBookByID(@PathVariable int id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        return optionalBook.orElse(null);
    }
}
