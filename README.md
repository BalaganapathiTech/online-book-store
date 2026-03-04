# 📚 Book Ordering System

A Spring Boot based REST API for managing books, members, and purchase orders.

This project demonstrates:

* Domain-driven design (DDD-style entities)
* Embedded value objects
* JPA relationships
* Validation
* Order lifecycle tracking
* OpenAPI (Swagger) documentation

---

## 🧱 Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySql Database
* Lombok
* springdoc-openapi (Swagger UI)

---

## 🏗 Project Structure

```
com.book
 ├── book
 ├── member
 ├── order
 └── common
```

Each module contains:

* `Domain` → Entities & value objects
* `DTO` → Request / Response models
* `Service` → Business logic
* `Controller` → REST endpoints
* `Repository` → JPA interfaces

---

## 🚀 How To Run

### 1. Clone the project

```
git clone https://github.com/BalaganapathiTech/online-book-store.git
cd online-book-store
```

### 2. Build

Maven:

```
mvn clean install
```

### 3. Run

```
mvn spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

## 🧪 API Documentation (Swagger)

Once the application is running:

```
http://localhost:8080/swagger-ui/
```

Swagger UI allows:

* Testing endpoints
* Viewing request/response models
* Understanding nested JSON structures

---

# 📦 API Endpoints

---

## 📘 Books

### Create Book

`POST /books`

```json
{
  "name": "Atomic Habit",
  "price": {
    "value": 200
  }
}
```

### Get All Books

`GET /books`

---

## 👤 Members

### Sign Up Member

`POST /members`

```json
{
  "email": {
    "value": "balaji@gmail.com"
  },
  "name": {
    "firstName": "Bala",
    "lastName": "Ganapathi"
  },
  "password": {
    "value": "1234"
  }
}
```

### Get Member By ID

`GET /members/{id}`

### Get All Members

`GET /members`

---

## 🛒 Orders

### Create Order

`POST /orders`

```json
{
  "memberId": 1,
  "orderBooks": [
    {
      "id": {
        "id": "BOOK_UUID"
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

### Get Order By Order Number

`GET /orders/{orderNumber}`

---

# 🧠 Domain Concepts

### Value Objects

* Email
* Password
* Name
* Mobile
* Address
* Money
* OrderNumber

All are implemented as `@Embeddable` objects to enforce domain integrity.

---

### Order Lifecycle

Order status flow:

```
PREPARING → PAYMENT_COMPLETE → DELIVERING → DELIVERY_COMPLETED
```

Order status history is persisted using:

* `OrderStatusHistory`
* `@OneToMany`
* Cascade operations enabled

---

# 🗄 Database

Default: MySql

Restarting the application resets all data.

To inspect DB:

```
http://localhost:8080/h2-console
```

(Configure credentials in application.properties)

---

# ⚠ Validation

Bean Validation is used:

* `@NotEmpty`
* `@Valid`
* Hibernate Validator

Invalid requests return:

```
400 Bad Request
```

---

# 🎯 What This Project Demonstrates

* Proper entity modeling
* Use of embedded value objects
* Clean separation of DTOs and domain
* JPA mappings (OneToMany, ManyToOne, EmbeddedId)
* RESTful API design
* Swagger integration

---

# 🔥 Future Improvements

* Add a global exception handler
* Add integration tests
* Add pagination
* Add soft delete
* Add authentication (JWT)
* Add Docker support
* Replace H2 with MySql

---

# 👤 Author

Balaganapathi
Backend Developer

---

# 📜 License

This project is for educational purposes.
