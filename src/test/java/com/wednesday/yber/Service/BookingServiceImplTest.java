package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.mapper.CabMapper;
import com.wednesday.yber.mapper.UserMapper;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.BookingsRepository;
import com.wednesday.yber.repository.CabDetailsRepository;
import com.wednesday.yber.repository.CabRepository;
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
    private CabRepository cabRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JOpenCageGeocoder jOpenCageGeocoder;

    User user1;
    User user2;

    Cab cab1;
    Cab cab2;

    UserMapper mapper;

    CabMapper cabmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingServiceImpl(bookingsRepository, cabRepository,
                userRepository ,jOpenCageGeocoder);

        user1 = User.builder().latitude(53.001).longitude(23.122).phoneNumber("988515283").build();
        cab1= Cab.builder().plateNumber("AP231234").build();

        user2 = User.builder().latitude(20.001).longitude(17.122).phoneNumber("988518283").build();
        cab2 = Cab.builder().plateNumber("JP123433").build();

        mapper = UserMapper.INSTANCE;
        cabmapper = CabMapper.INSTANCE;
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

        User user = User.builder().latitude(53.001).longitude(23.122).phoneNumber("9885349483").build();
        Cab cab = Cab.builder().plateNumber("AP231234").build();


        //Cab cabDetails = CabDetails.builder().cab(cab).carname("Nessie").Id(2L).build();

        Booking booking = Booking.builder().user(user).cab(cab).id(1L).build();
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));
        when(cabRepository.findCabByPlateNumber(anyString())).thenReturn(Optional.of(cab));
        when(bookingsRepository.save(any())).thenReturn(booking);
//        when(jOpenCageGeocoder.reverse(any())).thenReturn(new JOpenCageResponse());




        BookingDTO savedBooking = bookingService.bookRide(mapper.UserToUserDTO(user),cabmapper.CabToCabDTO(cab),"Cal public school", "");

        assertEquals(1 ,savedBooking.getId());
        verify(bookingsRepository,times(1)).save(any());

    }

    @Test
    void getPastBookings() {

        Booking booking = Booking.builder().id(1L).user(user1).cab(cab1).build();
        Booking booking1 = Booking.builder().id(2L).user(user1).cab(cab2).build();


        List<Booking> bookings = new ArrayList<>();

        when(bookingsRepository.findAllByUserPhoneNumber(any())).thenReturn(bookings);

        List<BookingDTO> resultBooking  = bookingService.getPastBookings(mapper.UserToUserDTO(user1));

        assertEquals(bookings.size(), resultBooking.size());

    }

    @Test
    void getBookingDetails() {
        Booking booking = Booking.builder().id(1L).user(user1).cab(cab1).build();
        Booking booking1 = Booking.builder().id(2L).user(user1).cab(cab2).build();

        List<Booking> bookings = new ArrayList<>();

        when(bookingsRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        BookingDTO bookingOptional = bookingService.getBookingDetails(1L);

        assertEquals(1L, bookingOptional.getId());
    }
}