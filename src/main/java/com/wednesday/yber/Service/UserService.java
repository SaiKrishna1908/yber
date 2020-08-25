package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.model.User;

public interface UserService {

    User findById(Long id);

    JOpenCageLatLng getUserLocation(Double Latitude, Double Longitude);


}
