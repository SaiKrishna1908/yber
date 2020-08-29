package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    static final BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDTO BookingToBookingDTO(Booking booking);
}
