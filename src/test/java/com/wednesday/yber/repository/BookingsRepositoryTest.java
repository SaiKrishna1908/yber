package com.wednesday.yber.repository;

import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class BookingsRepositoryIT {


    @Autowired
    private BookingsRepository bookingsRepository;

    @BeforeEach
    void setUp() {

        User user = User.builder().phoneNumber("34512").id(5L).firstName("jackie").lastName("chan").build();
        Booking booking = Booking.builder().source("I am source").destination("I am destination").id(1L)
                            .user(user).build();
        Booking booking1 = Booking.builder().source("I am Himalaya").destination("I am Some Himalaya").id(2L).build();
        Booking booking2 = Booking.builder().source("I am CALPS").destination("I am Hastinapuri").id(3L).build();

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        bookings.add(booking1);
        bookings.add(booking2);

    }

    @Test
    void saveTest(){


        Booking booking = Booking.builder().source("hyderabad").destination("ameerpet").user(User.builder().id(1L)
                            .phoneNumber("1234567890").build()).build();

        Booking savedBooking = bookingsRepository.save(booking);
        assertNotNull(savedBooking.getId());
        assertEquals(1L, savedBooking.getId());

    }

    @Test
    @Transactional
    void findById(){

        Booking booking = Booking.builder().source("hyderabad").destination("ameerpet").user(User.builder()
                .phoneNumber("1234567890").build()).build();

        Booking savedBooking = bookingsRepository.save(booking);

        Optional<Booking> bookingOptional = bookingsRepository.findById(savedBooking.getId());
        assertNotNull(bookingOptional.get());

    }

    @Test
    void findAll(){

        Booking booking = Booking.builder().source("I am source").destination("I am destination")
                .build();
        Booking booking1 = Booking.builder().source("I am Himalaya").destination("I am Some Himalaya").build();
        Booking booking2 = Booking.builder().source("I am CALPS").destination("I am Hastinapuri").build();

        bookingsRepository.save(booking);
        bookingsRepository.save(booking1);
        bookingsRepository.save(booking2);

        List<Booking> bookings = bookingsRepository.findAll();


        assertNotNull(bookings);
        assertEquals(3 ,bookings.size());
    }
}