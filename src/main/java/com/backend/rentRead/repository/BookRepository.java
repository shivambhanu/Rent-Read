package com.backend.rentRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.rentRead.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
