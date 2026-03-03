package com.book.Books.Service;

import com.book.Books.DTO.BookUpdateRequest;
import com.book.Books.Entity.Book;
import com.book.Books.Entity.BookId;
import com.book.Books.Exception.BookNotFoundException;
import com.book.Books.Repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BookHelperService {
    private final BookRepository bookRepository;

    public Book findById(final BookId id) {
        final Optional<Book> book = bookRepository.findById(id);
        book.orElseThrow(() -> new BookNotFoundException());
        return book.get();
    }
    public Book update(final BookId id, final BookUpdateRequest request) {
        final Book book = findById(id);
        book.update(request.getName(), request.getPrice());
        return book;
    }
    public void delete(final BookId id) {
        final Book book = findById(id);
        bookRepository.delete(book);
    }

    public List<Book> findByAll(){
        return bookRepository.findAll();
    }


}
