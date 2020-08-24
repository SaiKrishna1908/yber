package com.wednesday.yber.repository;

import com.wednesday.yber.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Long> {
}
