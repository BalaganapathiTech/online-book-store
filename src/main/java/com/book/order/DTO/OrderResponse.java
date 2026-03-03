package com.book.order.DTO;

import com.book.Member.DTO.OrderLineResponse;
import com.book.order.Entity.Money;
import com.book.order.Entity.Order;
import com.book.order.Entity.OrderNumber;
import com.book.order.Entity.Recipient;
import lombok.Getter;

import java.util.List;


@Getter
public class OrderResponse {

    private OrderNumber orderNumber;
    private Money totalAmount;
    private Recipient recipient;
    private List<OrderLineResponse> orderLineResponse;
    private List<OrderStatusHistoryResponse> orderStatusHistoryResponse;

    private OrderResponse(Order order) {
        this.orderNumber = order.getOrderNumber();
        this.totalAmount = order.getTotalAmount();
        this.recipient = order.getRecipient();
        this.orderLineResponse = OrderLineResponse.of(order.getOrderLines());
        this.orderStatusHistoryResponse = OrderStatusHistoryResponse.of(order.getOrderStatusHistories());
    }

    public static OrderResponse of(Order order) {
        return new OrderResponse(order);
    }
}
