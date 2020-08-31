package com.wednesday.yber.Service;

import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.model.Driver;
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
        Driver driver = new Driver();
        driver.setFirstName("ramesh");
        driver.setPhoneNumber("988518283");
        cab  = Cab.builder().isAvailable(true).plateNumber("123ABIO").driver(driver).build();

    }

    @Test
    void getCabDetails(){
        CabDetails cabDetails = CabDetails.builder().cab(cab).Id(1L).build();
        cab.setCabDetails(cabDetails);

        CabDetails result = cab.getCabDetails();

        assertEquals(cabDetails.getId(), cab.getCabDetails().getId());
    }


}