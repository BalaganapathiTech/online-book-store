package com.book.Books.Repository;

import com.book.Books.Entity.Book;
import com.book.Books.Entity.BookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, BookId> {
}