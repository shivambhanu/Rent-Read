# RentRead - Online Book Rental System

## Overview

**RentRead** is a RESTful API service built using Spring Boot to manage an online book rental system. It uses MySQL for data persistence and implements authentication and authorization to ensure secure access to its features.

## Problem Statement

Develop a RESTful API service to manage an online book rental system with the following key features:
- Authentication and authorization using Basic Auth.
- Two roles: USER and ADMIN.
- Public and private API endpoints, with access controlled based on roles.

## Key Features

### Authentication and Authorization
- Basic Auth for secure access.
- Two roles: USER and ADMIN.
- Public endpoints accessible by anyone.
- Private endpoints accessible by authenticated users only.
- Role-based access control for certain endpoints (e.g., only ADMIN can create books).

### User Management
- **User Registration:** Users can register by providing their email, password, first name, last name, and role (default role is USER if not specified).
- **User Login:** Users log in using their email and password. Passwords are encrypted using BCrypt.

### Book Management
- **Store and Manage Books:** Fields include Title, Author, Genre, and Availability Status.
- **Browse Books:** Any user can browse all available books.
- **Admin Privileges:** Only ADMIN can create, update, and delete books.

### Rental Management
- **Rent Books:** Users can rent books, with a limit of two active rentals at a time.
- **Return Books:** Users can return rented books.

## API Endpoints

### Public Endpoints
- **POST /register:** Register a new user.

### Private Endpoints (Requires Authentication)
- **GET /books:** Get all books (accessible by any authenticated user).
- **POST /books:** Create a new book (ADMIN only).
- **POST /books/{bookId}/rent:** Rent a book (USER).
- **POST /books/{bookId}/return:** Return a book (USER).

### Postman Collection
- **Collection File:** [Download API Postman Collection](./docs/Rent_Read.postman_collection.json)

To import the collection into Postman:
1. **Download** the JSON file from the link above.
2. **Open Postman** and go to **File > Import**.
3. **Select** the downloaded JSON file and import it.

## Database Schema

### User
- **id:** Long
- **email:** String
- **password:** String (encrypted)
- **firstName:** String
- **lastName:** String
- **role:** Role (USER/ADMIN)

### Book
- **bookId:** Long
- **title:** String
- **author:** String
- **genre:** String
- **availabilityStatus:** Boolean

### Rental
- **rentalId:** Long
- **user:** User
- **book:** Book
- **rentalDate:** LocalDate
- **returnDate:** LocalDate

## Error Handling

- **404 Not Found:** User not found, Book not found.
- **400 Bad Request:** Invalid request parameters.
- **403 Forbidden:** Access denied.
- **500 Internal Server Error:** Server-side errors.

## Logging

Logs are used to log information and errors to help with debugging and monitoring.

## Testing

Includes basic unit tests using MockMvc and Mockito. Minimum of 3 tests to cover key functionalities.


### Prerequisites
- JDK 11 or higher
- MySQL Database