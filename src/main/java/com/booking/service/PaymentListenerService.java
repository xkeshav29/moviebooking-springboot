package com.booking.service;

import com.booking.exception.ResourceNotFoundException;
import com.booking.repository.BookingRepository;
import com.booking.repository.SeatReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentListenerService {

    private BookingRepository bookingRepository;
    private SeatReservationRepository seatReservationRepository;

    @Autowired
    public PaymentListenerService(BookingRepository bookingRepository, SeatReservationRepository seatReservationRepository) {
        this.bookingRepository = bookingRepository;
        this.seatReservationRepository = seatReservationRepository;
    }

    @Transactional
    public void listen(Long bookingId, String paymentStatus) {
        // Get the bookingId from booking repo
        bookingRepository.findById(bookingId).map(booking -> {
            booking.setPaymentStatus("SUCCESSS");
            booking.setBookingStatus("SUCCESS");
            bookingRepository.save(booking);
            return booking;
        }).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));
        seatReservationRepository.findByBookingId(bookingId)
                .forEach(seatReservation -> seatReservation.setSeatStatus("CONFIRMED"));
    }


}
