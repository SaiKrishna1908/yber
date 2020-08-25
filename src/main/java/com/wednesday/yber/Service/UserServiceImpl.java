package com.wednesday.yber.Service;


import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public JOpenCageLatLng getUserLocation(Double Latitude, Double Longitude) {

        JOpenCageLatLng latLng = new JOpenCageLatLng();
        latLng.setLat(Latitude);
        latLng.setLng(Longitude);
        return latLng;

    }
}
