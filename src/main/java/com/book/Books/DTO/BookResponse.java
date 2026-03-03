package com.book.Books.DTO;

import com.book.Books.Entity.Book;
import com.book.Books.Entity.BookId;
import com.book.order.Entity.Money;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookResponse {

    private BookId bookId;
    private String name;
    private Money price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookResponse(final Book book) {
        this.bookId = book.getBookId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
    }

    public static BookResponse of(final Book book) {
        return new BookResponse(book);
    }

    public static List<BookResponse> of(List<Book> books) {
        return books.stream()
                .map(book ->  BookResponse.of(book))
                .collect(Collectors.toList());
    }
}
