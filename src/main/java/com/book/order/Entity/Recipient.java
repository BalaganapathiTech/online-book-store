package com.book.order.Entity;

import com.book.Member.Entity.Address;
import com.book.Member.Entity.Name;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipient {

    @Valid
    @Embedded
    private Mobile mobile;

    @Valid
    @Embedded
    private Name name;

    @Valid
    @Embedded
    private Address address;

    @Column(name = "shipping_message")
    private String shippingMessage;

    @Builder
    public Recipient(@Valid Mobile mobile, @Valid Name name, @Valid Address address, String shippingMessage) {
        this.mobile = mobile;
        this.name = name;
        this.address = address;
        this.shippingMessage = shippingMessage;
    }
}
