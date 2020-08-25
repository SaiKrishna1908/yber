package com.wednesday.yber.Service;

import com.wednesday.yber.model.Cab;
import com.wednesday.yber.repository.CabDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class CabDetailServiceImplTest {

    @Mock
    private  CabDetailsRepository cabDetailsRepository;

    Cab cab;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cab  = Cab.builder().isAvailable(true).plateNumber("123ABIO").driverName("ramesh").build();

    }


}