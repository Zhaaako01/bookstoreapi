package com.bookstore.bookstoreapi.repo;

import com.bookstore.bookstoreapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
