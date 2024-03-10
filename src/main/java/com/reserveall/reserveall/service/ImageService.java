package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.ImageResponseDto;
import com.reserveall.reserveall.model.Image;
import com.reserveall.reserveall.model.Project;
import com.reserveall.reserveall.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProjectService projectService;

    public ImageService(ImageRepository imageRepository, ProjectService projectService) {
        this.imageRepository = imageRepository;
        this.projectService = projectService;
    }

    private Image getOriginalImageById(String imageId) {
        return imageRepository.findById(imageId).orElseThrow();
    }

    private static ImageResponseDto convertToImageResponseDto(Image image){
        //TODO: sdcbjsdcbhsdc
        return null;
    }

    @Transactional
    public ImageResponseDto addImageToProject(String imageUrl, String projectId) {
        Project project = projectService.getOriginalProjectById(projectId);
        final Image fromDb = imageRepository.save(new Image(imageUrl, project));
        return new ImageResponseDto(fromDb.getId(), fromDb.getUrl());
    }

    @Transactional
    public ImageResponseDto removeImage(String imageId) {
        Image fromDb = getOriginalImageById(imageId);
        imageRepository.delete(fromDb);
        return new ImageResponseDto(fromDb.getId(), fromDb.getUrl());
    }

    public ImageResponseDto getImageById(String imageId) {
        Image fromDb = getOriginalImageById(imageId);
        return new ImageResponseDto(fromDb.getId(), fromDb.getUrl());
    }

}
