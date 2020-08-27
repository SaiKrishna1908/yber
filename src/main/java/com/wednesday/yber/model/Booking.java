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
@NoArgsConstructor
@Getter
@Setter
@Builder
public  class Booking implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cab_plate_number" ,referencedColumnName = "plateNumber")
    private Cab cab;

    private LocalDateTime date;
    private String source;
    private String destination;


}
