package com.book.order.Service;

import com.book.Books.Entity.Book;
import com.book.Books.Service.BookHelperService;
import com.book.Member.Entity.Member;
import com.book.Member.Service.MemberHelperService;
import com.book.order.Entity.Order;
import com.book.order.Entity.OrderLine;
import com.book.order.Entity.OrderNumber;
import com.book.order.DTO.OrderBook;
import com.book.order.DTO.OrderRequest;
import com.book.order.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OrderBookService {

    private final OrderRepository orderRepository;
    private final OrderHelperService orderHelperService;
    private final MemberHelperService memberHelperService;
    private final BookHelperService bookHelperService;

    public Order oderBook(final OrderRequest orderRequest) {
        final OrderNumber orderNumber = orderHelperService.generateCouponCode();
        final Member member = memberHelperService.findById(orderRequest.getMemberId());
        final List<OrderLine> orderLines = createOrderLines(orderRequest.getOrderBooks());
        final Order order = Order.of(orderNumber, member, orderLines, orderRequest.getRecipient());
        return orderRepository.save(order);
    }

    private List<OrderLine> createOrderLines(final List<OrderBook> orderRequest) {
        return orderRequest.stream()
                .map(orderBook -> {
                    final Book book = bookHelperService.findById(orderBook.getId());
                    return OrderLine.of(book, orderBook.getQuantity());
                }).collect(Collectors.toList());
    }

}
