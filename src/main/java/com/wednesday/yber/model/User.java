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
    public User(Long id,String firstName, String lastName, String email, String phoneNumber,
                    Double longitude, Double latitude) {
        super(id, firstName, lastName, email);

        this.phoneNumber = phoneNumber;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plate_number", referencedColumnName = "plateNumber")
    )
    private List<Cab> cabList = new ArrayList<>();

    private String phoneNumber;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Booking> userBookings = new ArrayList<>();

    private Double longitude;
    private Double latitude;


}
