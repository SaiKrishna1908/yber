package com.wednesday.yber.api.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DriverDTO {

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Double rating;
    private Long id;
}
