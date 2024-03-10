package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.ImageResponseDto;
import com.reserveall.reserveall.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("GetImageById")
    public ResponseEntity<ImageResponseDto> getImageById(@RequestParam String imageId){
        return ResponseEntity.ok(imageService.getImageById(imageId));
    }
}
