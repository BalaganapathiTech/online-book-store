package com.book.Books.Service;

import com.book.Books.Entity.Book;
import com.book.Books.DTO.BookCreateRequest;
import com.book.Books.Entity.BookId;
import com.book.Books.Repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCreateService {
    private final BookRepository bookRepository;

    public Book createBook(BookCreateRequest request){
        final Book book = Book.of(request);
        return bookRepository.save(book);
    }
}
