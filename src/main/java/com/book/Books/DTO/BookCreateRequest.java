package com.book.Books.DTO;

import com.book.order.Entity.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookCreateRequest {
    private String name;
    private Money price;
}
