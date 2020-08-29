package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.model.Cab;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CabDTOMapper {

    static final CabDTOMapper INSTANCE = Mappers.getMapper(CabDTOMapper.class);

    public Cab CabDTOToCab(CabDTO cabDTO);

}
