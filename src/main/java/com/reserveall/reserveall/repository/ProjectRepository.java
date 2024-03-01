package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
