package com.wednesday.yber.Service;

import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.User;

import java.util.List;

public interface BookingService {

    Booking findById(Long id);

    //TODO test for bookride

    BookingDTO bookRide(UserDTO userDTO , CabDTO cabDTO , String source, String destination);

    List<BookingDTO> getPastBookings(UserDTO userDTO);

    BookingDTO getBookingDetails(Long id);
}
