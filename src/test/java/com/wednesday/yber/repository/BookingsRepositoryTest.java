package com.wednesday.yber.repository;

import com.wednesday.yber.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
class BookingsRepositoryTest {


    @MockBean
    private BookingsRepository bookingsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Booking booking = Booking.builder().source("I am source").destination("I am destination").id(1L).build();
        Booking booking1 = Booking.builder().source("I am Himalaya").destination("I am Some Himalaya").id(2L).build();
        Booking booking2 = Booking.builder().source("I am CALPS").destination("I am Hastinapuri").id(3L).build();

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        bookings.add(booking1);
        bookings.add(booking2);

        //when
        when(bookingsRepository.save(any())).thenReturn(booking);
        when(bookingsRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        when(bookingsRepository.findAll()).thenReturn(bookings);
    }

    @Test
    void saveTest(){

        Booking booking = Booking.builder().source("I am source").destination("I am destination").build();
        assertNull(booking.getId());

        Booking savedBooking = bookingsRepository.save(booking);
        assertNotNull(savedBooking.getId());

    }

    @Test
    void findById(){

        Optional<Booking> booking= bookingsRepository.findById(1L);

        Booking result = booking.get();

        assertNotNull(result);
        assertEquals(1L, result.getId());

    }

    @Test
    void findAll(){

        List<Booking> bookings = bookingsRepository.findAll();
        assertNotNull(bookings);
        assertEquals(3 ,bookings.size());
    }
}