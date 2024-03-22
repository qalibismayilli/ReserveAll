package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUsername(String username);

}
