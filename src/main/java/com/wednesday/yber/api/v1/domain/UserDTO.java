package com.wednesday.yber.api.v1.domain;


import com.wednesday.yber.model.Cab;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{

    private Long id;
    private String phoneNumber;

    private List<CabDTO> cabList = new ArrayList<>();
    private List<BookingDTO> userBookings = new ArrayList<>();

    private Double longitude;
    private Double latitude;



    private String firstName;
    private String lastName;

    private String email;
    private String password;
}
