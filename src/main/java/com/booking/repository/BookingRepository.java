package com.booking.repository;

import com.booking.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select b from Booking b where b.createdAt=:date  AND b.bookingStatus=:bookingStatus")
    List<Booking> findBookingCreatedAt(String bookingStatus, Date date);
}
