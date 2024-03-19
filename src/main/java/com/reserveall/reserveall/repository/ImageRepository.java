package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.Image;
import com.reserveall.reserveall.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<List<Image>> findAllByProject(Project project);
}
