package com.book.Books.DTO;


import com.book.order.Entity.Money;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookUpdateRequest {
    private String name;
    private Money price;
}