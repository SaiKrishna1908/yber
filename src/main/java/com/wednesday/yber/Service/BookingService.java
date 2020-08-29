package com.wednesday.yber.Service;

import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.User;

import java.util.List;

public interface BookingService {

    Booking findById(Long id);

    //TODO test for bookride

    Booking bookRide(UserDTO userDTO , Cab cab , String source, String destination);

    List<Booking> getPastBookings(User user);

    Booking getBookingDetails(Long id);
}
