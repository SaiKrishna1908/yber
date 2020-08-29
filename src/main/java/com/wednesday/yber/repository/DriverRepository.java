package com.wednesday.yber.repository;

import com.wednesday.yber.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByPhoneNumber(String phoneNumber);

    List<Driver> findByFirstNameOrLastName(@Nullable String firstname, @Nullable  String lastname);
}
