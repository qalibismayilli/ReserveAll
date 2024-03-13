package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/addImagetoProject")
    public ResponseEntity<ImageResponseDto> addImageToProject(@RequestParam String imageUrl, @RequestParam String projectId){
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(imageService.addImageToProject(imageUrl, projectId));
    }

    @PostMapping("/removeImage")
    public ResponseEntity<ImageResponseDto> removeImage(@RequestParam String imageId){
        return ResponseEntity.ok(imageService.removeImage(imageId));
    }

    @GetMapping("/getImageById")
    public ResponseEntity<ImageResponseDto> getImageById(@RequestParam String imageId){
        return ResponseEntity.ok(imageService.getImageById(imageId));
    }

    @GetMapping("/getImagesByProject")
    public ResponseEntity<List<ImageResponseDto>> getImagesByProject(@RequestParam String projectId){
        return ResponseEntity.ok(imageService.getImagesByProject(projectId));
    }
}
