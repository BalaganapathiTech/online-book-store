package com.book.order.Repository;

import com.book.order.Entity.Order;
import com.book.order.Entity.OrderNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderNumber> {
}
