package com.wednesday.yber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wednesday.yber.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
