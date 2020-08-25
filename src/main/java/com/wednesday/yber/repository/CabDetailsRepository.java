package com.wednesday.yber.repository;

import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CabDetailsRepository extends JpaRepository<CabDetails, String > {

   CabDetails findCabDetailsByCab(Cab cab);


}
