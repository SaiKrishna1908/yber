package com.wednesday.yber.model;

import com.wednesday.yber.util.GeoLocation;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
class Cab implements Serializable {



    @Id
    @NonNull
    private String plateNumber;

    private String driverName;
    private Boolean isAvailable;
    private Long longitude;
    private Long latitude;


    @OneToOne
    private User user;

    @OneToOne
    private CabDetails cabDetails;

    @OneToMany(mappedBy = "cab")
    private List<Booking> cabBookings = new ArrayList<>();

//    public Cab addUser(User user , String destination){
//        if(!this.isAvailable)
//            throw new RuntimeException("Ride full");
//
//        //add user to this car
//        //add cardetails to this car
//        //add this trip to user's history
//        if( user.getId() != null){
//
//            GeoLocation geoLocation = new GeoLocation(user.getLongitude(), user.getLatitude());
//
//            //Book a ride
//
//            Booking booking = Booking.builder().date(LocalDateTime.now())
//                    .source(geoLocation.getPlace()).destination(destination).cab(this).build();
//
//            Booking savedBooking = boo
//            user.getUserBookings().add();
//
//        }
//    }

}
