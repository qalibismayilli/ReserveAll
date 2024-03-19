package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.model.Image;
import com.reserveall.reserveall.model.Project;
import com.reserveall.reserveall.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new ImageResponseDto(image.getId(), image.getUrl());
    }

    @Transactional
    public ImageResponseDto addImageToProject(String imageUrl, String projectId) {
        Project project = projectService.getOriginalProjectById(projectId);
        final Image fromDb = imageRepository.save(new Image(imageUrl, project));
        return convertToImageResponseDto(fromDb);
    }

    @Transactional
    public ImageResponseDto removeImage(String imageId) {
        Image fromDb = getOriginalImageById(imageId);
        imageRepository.delete(fromDb);
        return convertToImageResponseDto(fromDb);
    }

    public List<ImageResponseDto> getImagesByProject(String projectId){
        Project project = projectService.getOriginalProjectById(projectId);
        return imageRepository.findAllByProject(project).orElseThrow()
                .stream().map(p ->convertToImageResponseDto(p)).toList();
    }


}
