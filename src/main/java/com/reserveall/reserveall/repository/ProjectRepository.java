package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, String> {
    Optional<List<Project>> getProjectsByName(String projectName);
}
