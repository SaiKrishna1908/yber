package com.wednesday.yber.Service;

import com.wednesday.yber.api.v1.domain.CabDTO;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.model.Cab;

import java.util.List;

public interface CabService {

    //TODO: Unit tests

    CabDTO findByPlateNumber(String plateNumber);

    List<CabDTO> findCabs(UserDTO userDTO, String source);
}
