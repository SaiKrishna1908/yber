package com.wednesday.yber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wednesday.yber.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByIdOrPhoneNumber(Long id, String phone);
}
