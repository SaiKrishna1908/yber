package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.BookingsRepository;
import com.wednesday.yber.repository.CabDetailsRepository;
import com.wednesday.yber.repository.UserRepository;
import com.wednesday.yber.util.GeoLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingsRepository bookingsRepository;
    private  final CabDetailsRepository cabDetailsRepository;
    private final UserRepository userRepository;
    private final JOpenCageGeocoder jOpenCageGeocoder;

    private  GeoLocation geoLocation ;

    @Override
    public Booking findById(Long id) {
        if(id==null){
            log.debug("Null id");
            return null;
        }

        Optional<Booking> bookingOptional = bookingsRepository.findById(id);

        return bookingOptional.orElseGet(() -> null);

    }


    @Override
    @Transactional
    public Booking bookRide(UserDTO userDTO, Cab cab,String source, String destination) {





        if(userDTO.getPhoneNumber()==null || cab.getPlateNumber() == null ) {
            log.debug(userDTO.getPhoneNumber()+" booked a invalid cab " + cab.getPlateNumber());
            throw new RuntimeException("Invalid user or cab");
        }

        geoLocation = new GeoLocation(jOpenCageGeocoder);

        Booking booking = Booking.builder().user(userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).get())
                        .cab(cabDetailsRepository.findCabDetailsByCab(cab).getCab())
                        .source(source).destination(destination)
                .date(LocalDateTime.now()).build();

        return bookingsRepository.save(booking);
    }

    @Override
    public List<Booking> getPastBookings(User user) {

        return bookingsRepository.findAllByUserPhoneNumber(user.getPhoneNumber());
    }

    @Override
    public Booking getBookingDetails(Long id) {
        return bookingsRepository.findById(id).get();
    }
}
