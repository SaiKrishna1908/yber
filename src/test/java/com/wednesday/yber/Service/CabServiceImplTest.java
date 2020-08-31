package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.mapper.CabMapper;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.CabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CabServiceImplTest {

    @Mock
    CabRepository cabRepository;

    @Mock
    JOpenCageGeocoder jOpenCageGeocoder;

    CabMapper cabMapper ;


    CabService cabService;

    Cab cab1;
    Cab cab2;
    Cab cab3;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        cabMapper = CabMapper.INSTANCE;

        cabService = new CabServiceImpl(jOpenCageGeocoder, cabRepository);

        cab1 = Cab.builder().plateNumber("123456").build();
        cab2 = Cab.builder().plateNumber("234567").build();
        cab3 = Cab.builder().plateNumber("345678").build();

        User user = User.builder().phoneNumber("123456789").id(1L).firstName("Mahesh").lastName("agarwal")
                    .longitude(12.34).latitude(13.45).build();
    }

    @Test
    void findByPlateNumber() {

        when(cabRepository.findCabByPlateNumber(anyString())).thenReturn(Optional.of(cab1));

        CabDTO cabDTO = cabService.findByPlateNumber("123456");

        assertEquals(cab1.getPlateNumber(), cabDTO.getPlateNumber());
        verify(cabRepository, times(1)).findCabByPlateNumber(anyString());

    }

    @Test
    void findCabs() {

        ArrayList<Cab> cabs = new ArrayList<>();

        cabs.add(cab1);
        cabs.add(cab2);


        when(cabRepository.findCabsByLatitudeBetweenAndLongitudeBetween(anyDouble(), anyDouble(),anyDouble(),
                            anyDouble()))
                        .thenReturn(cabs);

        List<Cab> cabsFound = cabRepository
                .findCabsByLatitudeBetweenAndLongitudeBetween
                        (12.0,2.10,3.2,5.2);

        assertEquals(2, cabsFound.size());
        verify(cabRepository, times(1))
                .findCabsByLatitudeBetweenAndLongitudeBetween
                        (12.0,2.10,3.2,5.2);
    }
}