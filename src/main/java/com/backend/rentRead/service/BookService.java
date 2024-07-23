package com.backend.rentRead.service;

import com.backend.rentRead.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.backend.rentRead.model.Book;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book with the given id does not exists"));
    }


    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBookDetails(Long bookId, Book newBook){
        Book currBook = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with given id"));

        currBook.setTitle(newBook.getTitle());
        currBook.setAuthor(newBook.getAuthor());
        currBook.setGenre(newBook.getGenre());
        currBook.setAvailable(newBook.isAvailable());

        return bookRepository.save(currBook);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void removeBook(Long bookId){
        bookRepository.deleteById(bookId);
    }
}
