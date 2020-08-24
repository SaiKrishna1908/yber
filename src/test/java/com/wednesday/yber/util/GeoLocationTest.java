package com.wednesday.yber.util;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.wednesday.yber.config.JOpenCageConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JOpenCageConfig.class})
class GeoLocationTest {

    @MockBean
    JOpenCageGeocoder openCageGeocoder;

    JOpenCageReverseRequest jOpenCageReverseRequest;
    JOpenCageForwardRequest jOpenCageForwardRequest;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

        //It is a school
        jOpenCageReverseRequest = new JOpenCageReverseRequest(17.4828, 78.5642);
        jOpenCageReverseRequest.setLanguage("en");
        jOpenCageReverseRequest.setNoDedupe(true);
        jOpenCageReverseRequest.setLimit(5);
        jOpenCageReverseRequest.setNoAnnotations(true);
        jOpenCageReverseRequest.setMinConfidence(3);



        jOpenCageForwardRequest = new JOpenCageForwardRequest("Cal Public School India Hyderabad");
        jOpenCageForwardRequest.setRestrictToCountryCode("in");



    }

    @Test
    void getPlace() {


        when(openCageGeocoder.reverse(any())).thenReturn(new JOpenCageResponse());

        assertNotNull(openCageGeocoder);

        JOpenCageResponse rev_response = openCageGeocoder.reverse(jOpenCageReverseRequest);

        assertNotNull(rev_response);
        assertTrue(rev_response instanceof  JOpenCageResponse);

    }

    @Test
    void getCoordinates() {

        when(openCageGeocoder.forward(any())).thenReturn(new JOpenCageResponse());


        JOpenCageResponse for_response = openCageGeocoder.forward(jOpenCageForwardRequest);

        JOpenCageLatLng latLng =for_response.getFirstPosition();

        assertNotNull(for_response);
        assertTrue(for_response instanceof JOpenCageResponse);



    }
}