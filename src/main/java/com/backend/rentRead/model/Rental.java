package com.backend.rentRead.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private Book book;


    @Column(nullable = false)
    private LocalDate rentalDate;


    @Column
    private LocalDate returnDate;
}
