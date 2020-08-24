package com.wednesday.yber.util;


import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.*;
import com.wednesday.yber.config.JOpenCageConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



public class GeoLocation {

    private Double Longitude;
    private Double Latitude;

    @Autowired
    private JOpenCageGeocoder openCageGeocoder;

    public GeoLocation(Double Latitude, Double Longitude){
        this.Latitude = Latitude;
        this.Longitude  =Longitude;
    }



    public String getPlace(){
        JOpenCageReverseRequest request = new JOpenCageReverseRequest(Latitude, Longitude);

        request.setLanguage("en");
        request.setNoDedupe(true);
        request.setLimit(5);
        request.setNoAnnotations(true);
        request.setMinConfidence(3);


        JOpenCageResponse response = openCageGeocoder.reverse(request);

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
