package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.model.Driver;
import com.wednesday.yber.repository.DriverRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @Mock
    private DriverRepository driverRepository;

    private DriverService driverService;

    private Driver driver1;
    private Driver driver2;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

        driverService = new DriverServiceImpl(driverRepository);
        //given
        driver1 = Driver.builder().phoneNumber("988518283").firstName("Ramesh").build();
        driver2 = Driver.builder().phoneNumber("978517282").lastName("Ramesh").build();
    }

    @Test
    void findByName() {

        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);

        when(driverRepository.findByFirstNameOrLastName(anyString(),anyString())).thenReturn(drivers);

        List<Driver> driverList= driverService.findByName("ramesh", "");

        assertEquals(2 , driverList.size());
        verify(driverRepository, times(1)).findByFirstNameOrLastName(anyString(),anyString());
    }

    @Test
    void getDriverLocation() {

        Double a = 12.001;
        Double b = 15.012;

        JOpenCageLatLng jOpenCageLatLng = driverService.getDriverLocation(a,b);

        assertNotNull(jOpenCageLatLng);
    }

    @Test
    void findByPhoneNumber() {

        when(driverRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(driver1));

        Driver result = driverService.findByPhoneNumber("988518283");

//        assertNotNull(result);
        assertEquals("988518283", result.getPhoneNumber());
    }
}