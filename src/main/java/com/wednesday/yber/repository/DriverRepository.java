package com.wednesday.yber.repository;

import com.wednesday.yber.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByFirstNameOrLastName(String name);
}
