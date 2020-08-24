package com.wednesday.yber.model;


import com.wednesday.yber.repository.BookingsRepository;
import lombok.*;
import org.hibernate.InvalidMappingException;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public  class Booking implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userBookings")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cabBookings" ,referencedColumnName = "plateNumber")
    private Cab cab;

    private LocalDateTime date;
    private String source;
    private String destination;


}
