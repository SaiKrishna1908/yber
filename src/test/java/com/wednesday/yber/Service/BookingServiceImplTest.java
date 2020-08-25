package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.BookingsRepository;
import com.wednesday.yber.repository.CabDetailsRepository;
import com.wednesday.yber.repository.UserRepository;
import com.wednesday.yber.util.GeoLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class BookingServiceImplTest {


    BookingService bookingService;

    @Mock
    private BookingsRepository bookingsRepository;

    @Mock
    private CabDetailsRepository cabDetailsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JOpenCageGeocoder jOpenCageGeocoder;

    User user1;
    User user2;

    Cab cab1;
    Cab cab2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingServiceImpl(bookingsRepository, cabDetailsRepository,
                userRepository ,jOpenCageGeocoder);

        user1 = User.builder().latitude(53.001).longitude(23.122).id(1L).build();
        cab1= Cab.builder().plateNumber("AP231234").build();

        user2 = User.builder().latitude(20.001).longitude(17.122).id(2L).build();
        cab2 = Cab.builder().plateNumber("JP123433").build();


    }

    @Test
    void findById() {

        Booking booking = Booking.builder().id(1L).user(User.builder().firstName("sai").build()).build();

        when(bookingsRepository.findById(any())).thenReturn(Optional.of(booking));

        Booking result = bookingService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("sai", result.getUser().getFirstName());
    }

    @Test
    void bookRide() {

        GeoLocation geoLocation = new GeoLocation(jOpenCageGeocoder);

        User user = User.builder().latitude(53.001).longitude(23.122).id(1L).build();
        Cab cab = Cab.builder().plateNumber("AP231234").build();

        CabDetails cabDetails = CabDetails.builder().cab(cab).carname("Nessie").Id(2L).build();

        Booking booking = Booking.builder().user(user).cab(cab).id(1L).build();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(cabDetailsRepository.findCabDetailsByCab(any())).thenReturn(cabDetails);
        when(bookingsRepository.save(any())).thenReturn(booking);
        when(jOpenCageGeocoder.reverse(any())).thenReturn(new JOpenCageResponse());




        Booking savedBooking = bookingService.bookRide(user,cab,"Cal public school");

        assertEquals(1 ,savedBooking.getId());
        verify(bookingsRepository,times(1)).save(any());

    }

    @Test
    void getPastBookings() {

        Booking booking = Booking.builder().id(1L).user(user1).cab(cab1).build();
        Booking booking1 = Booking.builder().id(2L).user(user1).cab(cab2).build();


        List<Booking> bookings = new ArrayList<>();

        when(bookingsRepository.findAllByUserId(any())).thenReturn(bookings);

        List<Booking> resultBooking  = bookingService.getPastBookings(user1);

        assertEquals(bookings.size(), resultBooking.size());

    }

    @Test
    void getBookingDetails() {
        Booking booking = Booking.builder().id(1L).user(user1).cab(cab1).build();
        Booking booking1 = Booking.builder().id(2L).user(user1).cab(cab2).build();

        List<Booking> bookings = new ArrayList<>();

        when(bookingsRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        Booking bookingOptional = bookingService.getBookingDetails(1L);

        assertEquals(1L, bookingOptional.getId());
    }
}