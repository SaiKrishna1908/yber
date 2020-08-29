package com.wednesday.yber.model;


import lombok.*;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Person implements Serializable {

    @Id
    private String  phoneNumber;


    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;





}
