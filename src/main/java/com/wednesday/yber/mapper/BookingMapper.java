package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    static final BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "booking.user.firstName", target = "userName")
    @Mapping(source = "booking.cab.plateNumber", target = "plateNumber")
    @Mapping(source = "booking.cab.driver.firstName" , target = "driverName")
    BookingDTO BookingToBookingDTO(Booking booking);
}
