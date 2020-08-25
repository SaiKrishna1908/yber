package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
        user = User.builder().latitude(12.34).longitude(23.12).id(1L).build();
    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User userOptional= userService.findById(1L);
        assertEquals(1L, userOptional.getId());
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