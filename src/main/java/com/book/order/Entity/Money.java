package com.book.order.Entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public static Money of(Integer value) {
        return new Money(value);
    }
}
