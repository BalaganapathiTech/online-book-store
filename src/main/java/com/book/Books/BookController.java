package com.book.Books;

import com.book.Books.DTO.BookCreateRequest;
import com.book.Books.DTO.BookResponse;
import com.book.Books.DTO.BookUpdateRequest;
import com.book.Books.Entity.BookId;
import com.book.Books.Service.BookCreateService;
import com.book.Books.Service.BookHelperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookCreateService bookCreateService;
    private final BookHelperService bookHelperService;
    @PostMapping
    public BookResponse createBook(@RequestBody final BookCreateRequest request){
        return BookResponse.of(bookCreateService.createBook(request));
    }
    @GetMapping
    public List<BookResponse> findBooks(){
        return BookResponse.of(bookHelperService.findByAll());
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        final BookId bookId = BookId.of(id);
        bookHelperService.delete(bookId);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable String id,
            @RequestBody final BookUpdateRequest request) {

        final BookId bookId = BookId.of(id);
        return BookResponse.of(bookHelperService.update(bookId, request));
    }
}