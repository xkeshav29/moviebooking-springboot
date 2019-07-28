package com.booking.controller;

import com.booking.service.BookingService;
import com.booking.model.entity.Booking;
import com.booking.model.viewmodel.BookingViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/booking/{id}")
    public Booking getBooking(@PathVariable(value = "id") Long bookingId) {
        return bookingService.findById(bookingId);
    }

    @PostMapping("/booking")
    public BookingViewModel createBooking(@Valid @RequestBody BookingViewModel booking) {
        return bookingService.createBooking(booking);
    }

    @DeleteMapping("booking/{id}")
    public void deleteBooking(@PathVariable(value = "id") Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
