package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.ClientMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientMessageRepository extends JpaRepository<ClientMessage, String> {
}
