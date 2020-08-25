package com.wednesday.yber.Service;

import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.User;

import java.util.List;

public interface BookingService {

    Booking findById(Long id);

    Booking bookRide(User user ,Cab cab, String destination);

    List<Booking> getPastBookings(User user);

    Booking getBookingDetails(Long id);
}
