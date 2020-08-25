package com.wednesday.yber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Driver extends Person implements Serializable {

    @Builder
    public Driver(Long id, String firstName, String lastName, String email, String phoneNumber, Integer rating) {

        super(id,firstName, lastName, email);

        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    private String phoneNumber;
    private Integer rating;


}
