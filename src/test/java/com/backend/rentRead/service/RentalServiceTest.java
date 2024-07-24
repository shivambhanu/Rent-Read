package com.backend.rentRead.service;

import com.backend.rentRead.model.Rental;
import com.backend.rentRead.model.User;
import com.backend.rentRead.model.Book;
import com.backend.rentRead.model.enums.Role;
import com.backend.rentRead.repository.BookRepository;
import com.backend.rentRead.repository.RentalRepository;
import com.backend.rentRead.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {
    @Mock
    private RentalRepository rentalRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private BookRepository bookRepositoryMock;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private RentalService rentalService;

//    @BeforeEach
//    public void setUp(){
//    }

    @Test
    public void testRentBook(){
        //Arrange
        Long bookId = 1L, userId = 1L, rentalId = 1L;
        Book testBook = new Book(bookId, "The Compound Effect", "Darren Hardy", "Self-Help", true);
        User testUser = new User(userId, "testUser@email.com", "test", "user", "testPassword", Role.USER);
        Rental expectedRental = new Rental(rentalId, testUser, testBook, LocalDate.now(), null);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(testUser);
        SecurityContextHolder.setContext((securityContext));

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testBook));
        when(rentalRepositoryMock.countByUserAndReturnDateIsNull(any(User.class))).thenReturn(1L);
        when(rentalRepositoryMock.save(any(Rental.class))).thenReturn(expectedRental);

        //Act
        Rental actualRental = rentalService.rentBook(bookId);

        //Assert
        assertNotNull(actualRental);
        assertEquals(testBook, actualRental.getBook());
        assertEquals(testUser, actualRental.getUser());
        assertEquals(LocalDate.now(), actualRental.getRentalDate());
        assertNull(actualRental.getReturnDate());

        verify(bookRepositoryMock, times(1)).save(testBook);
        verify(rentalRepositoryMock, times(1)).countByUserAndReturnDateIsNull(any(User.class));
        verify(rentalRepositoryMock, times(1)).save(any(Rental.class));
    }
}
