package com.booking;

import com.booking.annotation.SingleExecutionCron;
import com.booking.model.entity.Booking;
import com.booking.repository.BookingRepository;
import com.booking.repository.SeatReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingStatusScheduler {

    private BookingRepository bookingRepository;
    private SeatReservationRepository reservationRepository;

    private static Logger LOG = LoggerFactory
            .getLogger(BookingStatusScheduler.class);

    @Autowired
    public BookingStatusScheduler(BookingRepository bookingRepository,
                                  SeatReservationRepository reservationRepository) {
        this.bookingRepository = bookingRepository;
        this.reservationRepository = reservationRepository;
    }

    //dummy comment 2
    @SingleExecutionCron(cronName = "booking_status")
    @Scheduled(cron = "0 0/1 * * * *")
    public void execute() {
        String time = Calendar.getInstance().getTime().toString();
        try {
            LOG.info("Scheduler started at {}", time);
            // find all the booking whose payment status is Pending for last 15 mins AND Update the booking status to CANCEL
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 15);
            List<Booking> pendingBookings = bookingRepository.findBookingCreatedAt("PENDING",
                    calendar.getTime());
            LOG.info("Found {} pending bookings", pendingBookings.size());
            pendingBookings.forEach(b -> LOG.info("Cancelling pending bookings {}", b));
            pendingBookings.forEach(this::updateStatus);
            LOG.info("Scheduler started at {} ended", time);
        } catch (Exception e) {
            LOG.error("Error running scheduler at {}", time, e);
        }
    }

    @Transactional
    private void updateStatus(Booking booking) {
        booking.setBookingStatus("CANCELLED");
        booking.setPaymentStatus("TIMED_OUT");
        bookingRepository.save(booking);
        reservationRepository.saveAll(reservationRepository.findByBookingId(booking.getId()).stream()
                .map(seatReservation -> seatReservation.updateStatus("AVAILABLE"))
                .collect(Collectors.toList()));
    }

}
