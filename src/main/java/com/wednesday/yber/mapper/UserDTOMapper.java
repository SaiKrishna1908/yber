package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOMapper {

     static final UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    public User UserDTOToUser(UserDTO userDTO);
}
