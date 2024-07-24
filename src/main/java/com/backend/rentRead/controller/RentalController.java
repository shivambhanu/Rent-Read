package com.backend.rentRead.controller;

import com.backend.rentRead.model.Rental;
import com.backend.rentRead.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;


    @PostMapping("/books/{bookId}/rent")
    public ResponseEntity<Rental> rentBook(@PathVariable Long bookId){
        return new ResponseEntity<>(rentalService.rentBook(bookId), HttpStatus.OK);
    }


    @PostMapping("/books/{bookId}/return")
    public ResponseEntity<Rental> returnBook(@PathVariable Long bookId){
        return new ResponseEntity<>(rentalService.returnBook(bookId), HttpStatus.OK);
    }
}
