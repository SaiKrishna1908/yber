package com.wednesday.yber.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends Person implements Serializable {


    @Builder
    public User(Long id, String phoneNumber,String firstName, String lastName, String email,
                    Double longitude, Double latitude, String password) {
        super(phoneNumber,id, firstName, lastName, email, password);

        this.longitude = longitude;
        this.latitude = latitude;

    }



    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plate_number", referencedColumnName = "plateNumber")
    )
    private List<Cab> cabList = new ArrayList<>();


    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Booking> userBookings = new ArrayList<>();



    private Double longitude;
    private Double latitude;


}
