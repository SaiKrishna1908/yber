package com.wednesday.yber.Service;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.model.User;

public interface UserService {

    User findByPhoneNumber(String phoneNumber);

    JOpenCageLatLng getUserLocation(Double Latitude, Double Longitude);

    //TODO Write tests
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);


}
