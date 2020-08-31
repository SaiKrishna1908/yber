package com.wednesday.yber.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends Person implements Serializable {

    @Builder
    public Driver(Long id, String phoneNumber, String firstName, String lastName, String email,
                  Double rating, String password) {

        super( phoneNumber,id,firstName, lastName, email, password);

        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }


    private String phoneNumber;
    private Double rating;


}
