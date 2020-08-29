package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.DriverDTO;
import com.wednesday.yber.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverMapper {

     static final DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    DriverDTO DriverToDriverDTO(Driver driver);
}
