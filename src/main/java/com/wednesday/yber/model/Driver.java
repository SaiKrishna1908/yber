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
    public Driver(Long id, String firstName, String lastName, String email, String phoneNumber, Double rating) {

        super(id,firstName, lastName, email);

        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    private String phoneNumber;
    private Double rating;


}
