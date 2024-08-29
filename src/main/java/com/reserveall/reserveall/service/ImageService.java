package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.ImageModel;
import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.exception.GenericException;
import com.reserveall.reserveall.model.Image;
import com.reserveall.reserveall.model.Project;
import com.reserveall.reserveall.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProjectService projectService;
    private final CloudinaryServiceImpl cloudinaryService;

    public ImageService(ImageRepository imageRepository, ProjectService projectService, CloudinaryServiceImpl cloudinaryService) {
        this.imageRepository = imageRepository;
        this.projectService = projectService;
        this.cloudinaryService = cloudinaryService;
    }


    private static ImageResponseDto convertToImageResponseDto(Image image){
        return new ImageResponseDto(image.getId(), image.getUrl());
    }

    @Transactional
    public ImageResponseDto addImageToProject(String imageId, String projectId) {
        try{
            imageRepository.addProjectToImage(projectId, imageId);
        }catch (Exception ex){
            throw new GenericException("not found image or project for this information");
        }

        Image fromDb = imageRepository.findById(imageId).get();
        return convertToImageResponseDto(fromDb);
    }

    @Transactional
    public ImageResponseDto removeImage(String imageId) {
        Image fromDb = imageRepository.findById(imageId)
                .orElseThrow(()-> new GenericException("not found image by id:" + imageId));
        imageRepository.delete(fromDb);
        return convertToImageResponseDto(fromDb);
    }

    public List<ImageResponseDto> getImagesByProject(String projectId){
        Project project = projectService.getOriginalProjectById(projectId);
        return imageRepository.findAllByProject(project).orElseThrow()
                .stream().map(p ->convertToImageResponseDto(p)).toList();
    }

    @Transactional
    public ResponseEntity<Map> uploadImage(ImageModel imageModel) {
        try {
            if (imageModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Image image = new Image(imageModel.getName(),
                    cloudinaryService.uploadFile(imageModel.getFile(), "folder_1"));

            if (image.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
