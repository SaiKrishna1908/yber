package com.wednesday.yber.api.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

    private  UserDTO userDTO;
    private CabDTO cabDTO;
    private LocalDateTime date;

    private String source;
    private String destination;

}
