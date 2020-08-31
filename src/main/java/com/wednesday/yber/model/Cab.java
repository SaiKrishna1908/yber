package com.wednesday.yber.model;


import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cab implements Serializable {



    @Id
    @NonNull
    private String plateNumber;

    @OneToOne
    private Driver driver;


    private Boolean isAvailable;
    private Double longitude;
    private Double latitude;


    @ManyToMany(mappedBy = "cabList")
    private List<User> user  = new ArrayList<>();

    @OneToOne
    private CabDetails cabDetails;

    @OneToMany(mappedBy = "cab")
    private List<Booking> cabBookings = new ArrayList<>();




}
