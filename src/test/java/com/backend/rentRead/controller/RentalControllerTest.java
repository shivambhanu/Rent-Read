package com.backend.rentRead.controller;

import com.backend.rentRead.model.Rental;
import com.backend.rentRead.model.enums.Role;
import com.backend.rentRead.service.RentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import com.backend.rentRead.model.User;
import com.backend.rentRead.model.Book;
import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalServiceMock;

    @Test
    @WithMockUser(username = "testUser@email.com", roles = "USER")
    public void testRentBook() throws Exception{
        Long bookId = 1L, userId = 1L, rentalId = 1L;
        Book book = new Book(bookId, "The Compound Effect", "Darren Hardy", "Self-Help", false);
        User user = new User(userId, "testUser@email.com", "test", "user", "testPassword", Role.USER);

        Rental rentalObj = new Rental(rentalId, user, book, LocalDate.now(), null);
        when(rentalServiceMock.rentBook(bookId)).thenReturn(rentalObj);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/{bookId}/rent", bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalId").value(rentalId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.userId").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.bookId").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.availabilityStatus").value(book.isAvailabilityStatus()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalDate").value(String.valueOf(LocalDate.now())));
    }


    @Test
    @WithMockUser(username = "testUser@email.com", roles = "USER")
    public void testReturnBook() throws Exception{
        Long bookId = 1L, userId = 1L, rentalId = 1L;
        Book book = new Book(bookId, "The Compound Effect", "Darren Hardy", "Self-Help", true);
        User user = new User(userId, "testUser@email.com", "test", "user", "testPassword", Role.USER);

        Rental rentalObj = new Rental(rentalId, user, book, LocalDate.now(), LocalDate.now().plusDays(1));
        when(rentalServiceMock.returnBook(bookId)).thenReturn(rentalObj);


        mockMvc.perform(MockMvcRequestBuilders.post("/books/{bookId}/return", bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalId").value(rentalId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user.userId").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.bookId").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.availabilityStatus").value(book.isAvailabilityStatus()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalDate").value(String.valueOf(LocalDate.now())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate").value(String.valueOf(LocalDate.now().plusDays(1))));
    }
}
