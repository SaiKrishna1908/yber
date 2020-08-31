package com.wednesday.yber.Service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.exceptions.PlaceNotFoundException;
import com.wednesday.yber.mapper.CabMapper;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.repository.CabRepository;
import com.wednesday.yber.util.GeoLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CabServiceImpl implements CabService{

    GeoLocation geoLocation;

    private final JOpenCageGeocoder jOpenCageGeocoder;
    private final CabRepository cabRepository;

    private final CabMapper cabMapper = CabMapper.INSTANCE;

    @Override
    public CabDTO findByPlateNumber(String plateNumber) {
        Optional<Cab> cab  = cabRepository.findCabByPlateNumber(plateNumber);

        if(cab.isPresent())
            return cabMapper.CabToCabDTO(cab.get());

        log.debug("Booking against a invalid cab");

        return null;
    }

    @Override
    public List<CabDTO> findCabs(UserDTO userDTO, String source) {

        geoLocation = new GeoLocation(jOpenCageGeocoder);


        JOpenCageLatLng jOpenCageLatLngSource = geoLocation.getCoordinates(source);

        //place not found
        if(jOpenCageLatLngSource == null)
            throw new PlaceNotFoundException("Place Not Found");

        List<Cab> cabs = cabRepository.findCabsByLatitudeBetweenAndLongitudeBetween(
                jOpenCageLatLngSource.getLat()-5, jOpenCageLatLngSource.getLat()+5,
                jOpenCageLatLngSource.getLng()-5, jOpenCageLatLngSource.getLng()+5
        );

        List<CabDTO> cabDTO = cabs.stream().map(cabMapper::CabToCabDTO).collect(Collectors.toList());

        return cabDTO;
    }
}
