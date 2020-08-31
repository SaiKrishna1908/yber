package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.mapper.UserMapper;
import com.wednesday.yber.model.ID;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.IDRepository;
import com.wednesday.yber.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private IDRepository idRepository;

    UserMapper userMapper ;
    private UserService userService;

    private User user;

    private ID id;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, idRepository);
        user = User.builder().latitude(12.34).longitude(23.12).phoneNumber("1234567890").
                password("password").build();

        id = new ID();
        id.setUser(user);
        id.setId(1L);

        userMapper = UserMapper.INSTANCE;
    }

    @Test
    void findById() {
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));
        User userOptional= userService.findByPhoneNumber("1234567890");
        assertEquals("1234567890", userOptional.getPhoneNumber());
    }

    @Test
    void getUserLocation() {

        Double a = 12.34;
        Double b = 23.12;

        JOpenCageLatLng jOpenCageLatLng = new JOpenCageLatLng();
        jOpenCageLatLng.setLng(user.getLongitude());
        jOpenCageLatLng.setLat(user.getLatitude());

        assertEquals(a, jOpenCageLatLng.getLat());
        assertEquals(b , jOpenCageLatLng.getLng());
    }

    @Test
    void createUserTest(){

        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());

        when(idRepository.save(any())).thenReturn(id);
        when(userRepository.save(any())).thenReturn(user);

        UserDTO userDTO = userMapper.UserToUserDTO(user);
        UserDTO result = userService.createUser(userDTO);


        assertEquals(userDTO.getId(), result.getId());
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(1)).findByPhoneNumber(anyString());
        verify(idRepository, times(2)).save(any());
    }

    @Test
    void getUserById(){
        when(idRepository.findById(anyLong())).thenReturn(Optional.of(id));

        UserDTO result = userService.getUserById(1L);

        assertEquals(id.getUser().getId(), result.getId());
        verify(idRepository, times(1)).findById(anyLong());

    }


    @Test
    void getIdByPhoneNumber(){
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));

        Long id = userService.getIdByPhoneNumber("1234567890");

        assertEquals(user.getId(), id);
        verify(userRepository, times(1)).findByPhoneNumber(anyString());
    }
}