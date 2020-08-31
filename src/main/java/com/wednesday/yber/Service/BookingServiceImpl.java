package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.mapper.BookingMapper;
import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.BookingsRepository;
import com.wednesday.yber.repository.CabDetailsRepository;
import com.wednesday.yber.repository.CabRepository;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingsRepository bookingsRepository;
    private  final CabRepository cabRepository;
    private final UserRepository userRepository;
    private final JOpenCageGeocoder jOpenCageGeocoder;
    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

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
    public BookingDTO bookRide(UserDTO userDTO, CabDTO cab, String source, String destination) {





        if(userDTO.getPhoneNumber()==null || cab.getPlateNumber() == null ) {
            log.debug(userDTO.getPhoneNumber()+" booked a invalid cab " + cab.getPlateNumber());
            throw new RuntimeException("Invalid user or cab");
        }


        Booking booking = Booking.builder().user(userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).get())
                        .cab(cabRepository.findCabByPlateNumber(cab.getPlateNumber()).get())
                        .source(source).destination(destination)
                .date(LocalDateTime.now()).build();

        Booking savedBooking  = bookingsRepository.save(booking);
        return bookingMapper.BookingToBookingDTO(savedBooking);
    }

    @Override
    public List<BookingDTO> getPastBookings(UserDTO userDTO) {

        return bookingsRepository.findAllByUserPhoneNumber(userDTO.getPhoneNumber())
                .stream().map(bookingMapper::BookingToBookingDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingDetails(Long id) {
        return
                bookingMapper.BookingToBookingDTO(bookingsRepository.findById(id).get());
    }
}
