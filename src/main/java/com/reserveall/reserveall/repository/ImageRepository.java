package com.reserveall.reserveall.repository;

import com.reserveall.reserveall.model.Image;
import com.reserveall.reserveall.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<List<Image>> findAllByProject(Project project);

    @Query("UPDATE Image i SET i.project.id = :projectId WHERE i.id = :imageId")
    @Modifying
    @Transactional
    void addProjectToImage(String projectId, String imageId);
}
