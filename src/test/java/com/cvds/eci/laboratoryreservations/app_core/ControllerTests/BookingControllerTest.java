/*
package com.cvds.eci.laboratoryreservations.app_core.ControllerTests;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import com.cvds.eci.laboratoryreservations.app_core.controller.BookingController;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    void testGetBookings() throws Exception {
        when(bookingService.getAllBookings()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testAddBooking() throws Exception {
        Booking booking = new Booking("Lab A", null, null, null, "Test Booking", 1, "user123");
        when(bookingService.addBooking(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("Test Booking"));
    }

    @Test
    void testDeleteBooking() throws Exception {
        String bookingId = "123";
        doNothing().when(bookingService).deleteBooking(bookingId);

        mockMvc.perform(delete("/api/bookings/{id}", bookingId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetBookingById() throws Exception {
        String bookingId = "123";
        Booking booking = new Booking("Lab A", null, null, null, "Test Booking", 1, "user123");
        when(bookingService.findById(bookingId)).thenReturn(booking);

        mockMvc.perform(get("/api/bookings/{id}", bookingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Test Booking"));
    }

    @Test
    void testGetBookingsByUserId() throws Exception {
        String userId = "user123";
        List<Booking> bookings = List.of(new Booking("Lab A", null, null, null, "User Booking", 1, userId));
        when(bookingService.findAllBookingsByUserId(userId)).thenReturn(bookings);

        mockMvc.perform(get("/api/bookings/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("User Booking"));
    }
}

*/