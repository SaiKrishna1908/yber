package com.wednesday.yber.Service;

import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Cab;

import java.util.List;

public interface CabService {

    List<Cab> findCabs(UserDTO userDTO, String source, String destination);
}
