package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.model.Driver;

import java.util.List;

public interface DriverService {

     List<Driver> findByName(String firstName ,String lastName);

     JOpenCageLatLng getDriverLocation(Double Latitude, Double Longitude);

     Driver findById(Long Id);
}
