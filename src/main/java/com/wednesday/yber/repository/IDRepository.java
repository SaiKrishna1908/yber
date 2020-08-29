package com.wednesday.yber.repository;

import com.wednesday.yber.model.ID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDRepository extends JpaRepository<ID, Long> {

    ID getIDByUser_PhoneNumberContaining(String phoneNumber);
}
