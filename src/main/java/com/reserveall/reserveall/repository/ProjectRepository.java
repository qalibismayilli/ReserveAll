package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, String> {
    Optional<Project> getProjectsByName(String projectName);
}
