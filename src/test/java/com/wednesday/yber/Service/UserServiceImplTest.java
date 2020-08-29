package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private IDRepository idRepository;

    private UserService userService;

    private User user;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, idRepository);
        user = User.builder().latitude(12.34).longitude(23.12).phoneNumber("1234567890").build();
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
}