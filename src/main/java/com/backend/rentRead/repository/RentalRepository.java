package com.backend.rentRead.repository;

import com.backend.rentRead.model.Rental;
import com.backend.rentRead.model.User;
import com.backend.rentRead.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentalRepository extends JpaRepository<Rental, Long> {
    long countByUserAndReturnDateIsNull(User user);
    Rental findByUserAndBookAndReturnDateIsNull(User user, Book book);
}
