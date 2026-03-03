package com.book.order.Service;

import com.book.order.Entity.Order;
import com.book.order.Entity.OrderNumber;
import com.book.order.Exception.OrderNotFoundException;
import com.book.order.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderHelperService {

    private final OrderRepository orderRepository;

    public OrderNumber generateCouponCode() {
        OrderNumber orderNumber = OrderNumber.generateOrderNumber();
        while(isCodeExist(orderNumber)){
            orderNumber = OrderNumber.generateOrderNumber();
        }
        return orderNumber;
    }

    public Order findByOrderNumber(OrderNumber orderNumber) {
        final Optional<Order> order = orderRepository.findById(orderNumber);
        order.orElseThrow(() -> new OrderNotFoundException());
        return order.get();
    }

    private boolean isCodeExist(final OrderNumber orderNumber) {
        return orderRepository.findById(orderNumber).isPresent();
    }
}
