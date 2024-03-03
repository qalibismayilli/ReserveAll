package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
