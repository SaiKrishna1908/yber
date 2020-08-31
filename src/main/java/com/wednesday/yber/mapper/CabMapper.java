package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.model.Cab;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CabMapper {

    static final CabMapper INSTANCE = Mappers.getMapper(CabMapper.class);

    @Mapping(source = "cab.driver.firstName", target = "driverName")
    CabDTO CabToCabDTO(Cab cab);


}
