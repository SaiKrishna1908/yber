package com.wednesday.yber.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

    private String phoneNumber;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Booking> userBookings = new ArrayList<>();

    private Double longitude;
    private Double latitude;


}
