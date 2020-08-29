package com.wednesday.yber.repository;

import com.wednesday.yber.model.Booking;
import com.wednesday.yber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking  b, User  u WHERE u.id = :id  ")
    List<Booking> findAllByUserPhoneNumber(@Param("id") String id);

}
