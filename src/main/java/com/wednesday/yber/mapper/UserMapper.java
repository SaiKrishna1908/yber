package com.wednesday.yber.mapper;

import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

     static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    public UserDTO UserToUserDTO(User user);
}

