package com.wednesday.yber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@Entity
public class User extends Person implements Serializable {


    @Builder
    public User(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    private String phoneNumber;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Booking> userBookings = new ArrayList<>();

    private Double longitude;
    private Double latitude;


}
