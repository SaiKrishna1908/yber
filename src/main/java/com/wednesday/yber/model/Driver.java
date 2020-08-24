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
@Builder
public class Driver extends Person implements Serializable {

    @Builder
    public Driver(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    private String phoneNumber;
    private Integer rating;


}
