package com.booking.model.viewmodel;

import com.booking.model.entity.Seat;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingViewModel {
    private String userId;
    private Long bookingId;
    private Long showid;
    private List<Seat> seats;
}
