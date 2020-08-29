package com.wednesday.yber.api.v1.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CabDTO {

    private String plateNumber;
    private DriverDTO driverDTO;
    private Double longitude;
    private Double latitude;


}
