package com.wednesday.yber.Service;

import com.wednesday.yber.model.ID;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.IDRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class IDServiceImplTest {

    @Mock
    IDRepository idRepository;
    User user;
    ID id;
    IDService idService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        idService = new IDServiceImpl(idRepository);

        user = User.builder().firstName("Ramesh").phoneNumber("983456").build();

       id = new ID();
       id.setUser(user);
       id.setId(1L);

       user.setId(id.getId());
    }

    @Test
    void getIDByPhoneNumber() {

        when(idRepository.getIDByUser_PhoneNumberContaining(anyString()))
                .thenReturn(id);

        ID result = idService.getIDByPhoneNumber("983456");

        assertEquals(1L, result.getId());
        verify(idRepository, times(1)).getIDByUser_PhoneNumberContaining(anyString());
    }
}