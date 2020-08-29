package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.BookingDTO;
import com.wednesday.yber.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingDTOMapper {

    static final BookingDTOMapper INSTANCE = Mappers.getMapper(BookingDTOMapper.class);

    public Booking BookingDTOToBooking(BookingDTO bookingDTO);
}
