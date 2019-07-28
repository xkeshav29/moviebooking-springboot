package com.booking.service;

import com.booking.exception.ResourceNotFoundException;
import com.booking.model.entity.Booking;
import com.booking.model.entity.Seat;
import com.booking.model.entity.SeatReservation;
import com.booking.model.viewmodel.BookingViewModel;
import com.booking.repository.BookingRepository;
import com.booking.repository.MovieShowRepository;
import com.booking.repository.SeatRepository;
import com.booking.repository.SeatReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private SeatRepository seatRepository;
    private SeatReservationRepository seatReservationRepository;
    private MovieShowRepository movieShowRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository,
                          SeatReservationRepository seatReservationRepository, MovieShowRepository movieShowRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.seatReservationRepository = seatReservationRepository;
        this.movieShowRepository = movieShowRepository;
    }

    @Transactional
    public BookingViewModel createBooking(BookingViewModel booking) {
        // validate the show if it is valid or not
        movieShowRepository.findById(booking.getShowid())
                .orElseThrow(() -> new ResourceNotFoundException("Show", "id", booking.getShowid()));

        // validate the seats sent by the user
        List<Long> requestedSeatIds = booking.getSeats().stream().map(Seat::getId).collect(Collectors.toList());
        List<Seat> seats = seatRepository.findAllById(requestedSeatIds);
       if(!seats.stream().map(Seat::getId).allMatch(requestedSeatIds::contains)) {
           throw new RuntimeException("Requested seats not found");
       }

       // check if the seats are available or not
        List<SeatReservation> reservedSeats = seatReservationRepository.findByBookingId(booking.getBookingId());
       if(!reservedSeats.stream().allMatch(s->"AVAILABLE".equals(s.getSeatStatus()))) {
           throw new RuntimeException("Requested seat(s) are already reserved");
       }

       // create the booking in Pending status and blocked the seats.
        Booking bookingEntity = Booking.builder()
                .bookingStatus("PENDING")
                .paymentStatus("PENDING")
                .build();
        bookingRepository.save(bookingEntity);
        List<SeatReservation> seatReservations = seats.stream()
                .map(seat -> new SeatReservation(booking.getBookingId(), "BLOCKED", bookingEntity))
                .collect(Collectors.toList());
        seatReservationRepository.saveAll(seatReservations);
        booking.setBookingId(bookingEntity.getId());
        return booking;
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
    }

    public void deleteBooking(Long id) {
        bookingRepository.findById(id)
                .ifPresent(booking -> bookingRepository.delete(booking));
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
