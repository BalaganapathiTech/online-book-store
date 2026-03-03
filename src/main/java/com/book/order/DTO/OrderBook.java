package com.book.order.DTO;

import com.book.Books.Entity.BookId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBook {

    private BookId id;
    private int quantity;

    @Builder
    public OrderBook(BookId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
