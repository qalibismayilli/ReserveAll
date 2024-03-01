package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
