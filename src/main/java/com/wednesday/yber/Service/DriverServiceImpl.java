package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.model.Driver;
import com.wednesday.yber.repository.DriverRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public List<Driver> findByName(String firstname, String lastname) {

       List<Driver> driver =  driverRepository.findByFirstNameOrLastName(firstname, lastname);

        return driver;
    }

    @Override
    public JOpenCageLatLng getDriverLocation(Double Latitude, Double Longitude) {

        JOpenCageLatLng latLng = new JOpenCageLatLng();
        latLng.setLat(Latitude);
        latLng.setLng(Longitude);
        return latLng;
    }

    @Override
    public Driver findById(Long Id) {
        Optional<Driver> driver = driverRepository.findById(Id);

        return driver.orElse(null);
    }
}
