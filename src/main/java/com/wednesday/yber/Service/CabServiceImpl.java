package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.repository.CabRepository;
import com.wednesday.yber.util.GeoLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CabServiceImpl implements CabService{

    GeoLocation geoLocation;

    private final JOpenCageGeocoder jOpenCageGeocoder;
    private final CabRepository cabRepository;



    @Override
    public List<Cab> findCabs(UserDTO userDTO, String source, String destination) {

        geoLocation = new GeoLocation(jOpenCageGeocoder);


        JOpenCageLatLng jOpenCageLatLngSource = geoLocation.getCoordinates(source);
        JOpenCageLatLng jOpenCageLatLngDestination = geoLocation.getCoordinates(destination);

        List<Cab> cabs = cabRepository.findCabsByLatitudeBetweenAndLongitudeBetween(
                jOpenCageLatLngSource.getLat()-0.5, jOpenCageLatLngSource.getLat()+0.5,
                jOpenCageLatLngSource.getLng()-0.5, jOpenCageLatLngSource.getLng()+0.5
        );

        return cabs;
    }
}
