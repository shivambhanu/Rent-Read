package com.backend.rentRead.service;

import com.backend.rentRead.model.Rental;
import com.backend.rentRead.model.User;
import com.backend.rentRead.model.Book;
import com.backend.rentRead.repository.BookRepository;
import com.backend.rentRead.repository.RentalRepository;
import com.backend.rentRead.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    public Rental rentBook(Long bookId){
        //Fetching user from security context.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();

        Long userId = userDetails.getUserId();
        User currUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with given id"));

        Book currBook = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with the given id"));
        if(!currBook.isAvailabilityStatus()){
            throw new IllegalStateException("Book is not available for rent");
        }

        //check whether the user has already rented 2 books
        long totalRentals = rentalRepository.countByUserAndReturnDateIsNull(currUser);
        if(totalRentals >= 2){
            throw new IllegalStateException("You cannot rent more than 2 books actively");
        }


        //if everything is fine above, then create a rental object
        Rental newRental = new Rental();
        newRental.setUser(currUser);
        newRental.setBook(currBook);
        newRental.setRentalDate(LocalDate.now());

        //set the book availability to false
        currBook.setAvailabilityStatus(false);
        bookRepository.save(currBook);

        return rentalRepository.save(newRental);
    }


    //Return rented books using this service
    public Rental returnBook(Long bookId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        User currUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with given id"));
        Book currBook = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with the given id"));

        Rental currRental = rentalRepository.findByUserAndBookAndReturnDateIsNull(currUser, currBook);
        if(currRental.getReturnDate() != null){
            throw new IllegalStateException("This book has already been returned");
        }
        currRental.setReturnDate(LocalDate.now());

        currBook.setAvailabilityStatus(true);
        bookRepository.save(currBook);

        return rentalRepository.save(currRental);
    }
}
