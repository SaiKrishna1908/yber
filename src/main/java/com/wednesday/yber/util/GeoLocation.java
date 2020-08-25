package com.wednesday.yber.util;


import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.*;
import com.wednesday.yber.config.JOpenCageConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public class GeoLocation {

    private Double Longitude;
    private Double Latitude;


    private final JOpenCageGeocoder openCageGeocoder;

    public GeoLocation(JOpenCageGeocoder openCageGeocoder){
        this.openCageGeocoder = openCageGeocoder;
    }



    public String getPlace(Double Latitude, Double Longitude){


        JOpenCageReverseRequest request = new JOpenCageReverseRequest(Latitude, Longitude);

        request.setLanguage("en");
        request.setNoDedupe(true);
        request.setLimit(5);
        request.setNoAnnotations(true);
        request.setMinConfidence(3);


        JOpenCageResponse response = openCageGeocoder.reverse(request);

        if(response.getResults() == null)
            return null;

        return response.getResults().get(0).getFormatted();
    }

    public JOpenCageLatLng getCoordinates(String address){

        JOpenCageForwardRequest jOpenCageRequest = new JOpenCageForwardRequest(address);
        jOpenCageRequest.setRestrictToCountryCode("in");

        JOpenCageResponse response = openCageGeocoder.forward(jOpenCageRequest);

        JOpenCageLatLng firstResultLatLong = response.getFirstPosition();

        return firstResultLatLong;
    }
}
