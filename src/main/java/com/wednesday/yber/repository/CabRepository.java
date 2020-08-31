package com.wednesday.yber.repository;

import com.wednesday.yber.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CabRepository extends JpaRepository<Cab, String> {

    List<Cab> findCabsByLatitudeBetweenAndLongitudeBetween(Double latitudeMinusOffset, Double latitudePlusOffet,
                                                           Double longitudeMinusOffset, Double longitudePlusOffset);

    Optional<Cab> findCabByPlateNumber(String plateNumber);
}
