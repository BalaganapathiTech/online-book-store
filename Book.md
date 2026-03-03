# Building a Book Ordering System with Spring Boot

### A Practical Case Study in Domain-Driven REST Design

---

# Chapter 1 — The Problem

We needed a backend system capable of:

1. Managing books
2. Registering members
3. Creating purchase orders
4. Tracking order lifecycle
5. Enforcing domain integrity
6. Handling nested value objects cleanly

This was not a CRUD demo.

It required:

* Embedded value objects
* Composite identifiers
* Order status history
* Domain-driven modeling
* Strict validation
* JSON ↔ domain mapping alignment

The core problem:

> How do we design a REST API that respects domain rules while still being easy to use?

---

# Chapter 2 — The Architecture

The system was structured into bounded domains:

```
book/
member/
order/
```

Each contained:

* domain
* dto
* service
* controller
* repository

The goal was separation of concerns:

* Controllers → HTTP
* Services → Business logic
* Domain → Rules & integrity
* DTO → Transport models
* Repository → Persistence

---

# Chapter 3 — Domain Modeling

## Value Objects

Instead of primitive obsession, we used embedded value objects.

Example: Email

```java
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @NotEmpty
    @Column(name = "email", nullable = false, unique = true)
    private String value;

    public Email(String value) {
        this.value = value;
    }
}
```

Why?

Because:

* Email has validation
* Email has identity
* Email is not “just a String”

The same principle was applied to:

* Name
* Password
* Mobile
* Money
* OrderNumber

---

# Chapter 4 — The Book Domain

A book contains:

* BookId (17,576,000 possible IDs)
* Name
* Price (Money)
* Timestamps

Books are simple. No lifecycle complexity.

Example request:

```json
{
  "name": "Atomic Habit",
  "price": {
    "value": 200
  }
}
```

Output:

```json
{
  "bookId": {
    "id": "NDI272"
  },
  "name": "Atomic Habit",
  "price": {
    "value": 200
  },
  "createdAt": "...",
  "updatedAt": "..."
}
```

---

# Chapter 5 — Member Registration

Members contain:

* Email (unique)
* Password
* Name
* Timestamps

We enforced duplication prevention:

```java
public void verifyEmailIsDuplicated(final Email email){
    if(memberRepository.findByEmail(email) != null)
        throw new MemberDuplicationException();
}
```

If duplicate:

→ 400 Bad Request

---

# Chapter 6 — The Hard Part: Orders

Orders required:

* Embedded OrderNumber
* OrderLines
* TotalAmount calculation
* Recipient object
* Status history
* Member relationship

Order creation logic:

```java
public Order oderBook(final OrderRequest orderRequest) {

    final OrderNumber orderNumber = orderHelperService.generateCouponCode();
    final Member member = memberHelperService.findById(orderRequest.getMemberId());
    final List<OrderLine> orderLines = createOrderLines(orderRequest.getOrderBooks());

    final Order order = Order.of(orderNumber, member, orderLines, orderRequest.getRecipient());

    return orderRepository.save(order);
}
```

Key design rule:

> TotalAmount is derived. Never passed from client.

Inside constructor:

```java
private Money getOrderLineTotal(List<OrderLine> orderLines) {
    final int total = orderLines.stream()
        .mapToInt(orderLine -> orderLine.getAmount().getValue())
        .sum();
    return Money.of(total);
}
```

This guarantees integrity.

Client cannot fake totals.

---

# Chapter 7 — The JSON Mapping Struggle

The biggest technical challenge:

Nested embedded objects + protected constructors.

Jackson requires:

* No-arg constructor
* Accessible fields

Several 400 errors occurred because:

* `@NoArgsConstructor(access = PROTECTED)`
* Embedded IDs
* Complex nested DTOs

Example failure:

```
HttpMessageNotReadableException
Required request body is missing
```

Root cause:

Mismatch between JSON structure and domain model.

Example fix:

If `BookId` contains:

```java
private String id;
```

Then JSON must be:

```json
"id" : {
  "id": "UUID"
}
```

Not:

```json
"id": "UUID"
```

Precision matters.

---

# Chapter 8 — Order Request Example

Final working order request:

```json
{
  "memberId": 1,
  "orderBooks": [
    {
      "id": {
        "id": "NDI272"
      },
      "quantity": 2
    }
  ],
  "recipient": {
    "mobile": {
      "value": "01012341234"
    },
    "name": {
      "firstName": "Bala",
      "lastName": "Ganapathi"
    },
    "address": {
      "city": "Madurai",
      "street": "4th street",
      "zipcode": "12345"
    },
    "shippingMessage": "Leave at the door"
  }
}
```

Response:

```json
{
  "orderNumber": {
    "number": "AB12CD34"
  },
  "totalAmount": {
    "value": 200
  },
  "recipient": {...},
  "orderLineResponse": [...],
  "orderStatusHistoryResponse": [
    {
      "orderStatus": "PREPARING"
    }
  ]
}
```

---

# Chapter 9 — Order Lifecycle

On creation:

Status = PREPARING

Stored in:

```
order_status_history
```

Cascade + orphan removal ensures:

Deleting order → deletes history.

---

# Chapter 10 — Lessons Learned

1. Domain modeling matters more than controllers.
2. Embedded value objects improve integrity.
3. JSON must match internal structure exactly.
4. Jackson + protected constructors can break APIs.
5. Swagger prevents blind debugging.
6. Total calculation must live in domain, not controller.
7. Avoid primitive obsession.
8. Always derive, never trust client input.

---

# Chapter 11 — Final Outcome

The system now supports:

* Book creation
* Member signup
* Duplicate prevention
* Order placement
* Total calculation
* Order status tracking
* Swagger documentation
* MySql database persistence

The architecture is:

* Clean
* Expandable
* Domain-focused
* Testable

---

# Final Thought

This project is no longer a CRUD demo.

It demonstrates:

* Embedded identity
* Domain integrity
* JPA relationships
* REST design discipline

That’s backend engineering.

Not button-click coding.