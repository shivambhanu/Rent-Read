package com.backend.rentRead.controller;

import com.backend.rentRead.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.rentRead.model.Book;
import java.util.List;


@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }


    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book){
        return new ResponseEntity<>(bookService.updateBookDetails(bookId, book), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable Long bookId){
        bookService.removeBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
