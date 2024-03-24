package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.ImageModel;
import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/admin/addImageToProject")
    public ResponseEntity<ImageResponseDto> addImageToProject(@RequestParam String imageId, @RequestParam String projectId){
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(imageService.addImageToProject(imageId, projectId));
    }

    @PostMapping("/admin/removeImage")
    public ResponseEntity<ImageResponseDto> removeImage(@RequestParam String imageId){
        return ResponseEntity.ok(imageService.removeImage(imageId));
    }


    @GetMapping("/getImagesByProject")
    public ResponseEntity<List<ImageResponseDto>> getImagesByProject(@RequestParam String projectId){
        return ResponseEntity.ok(imageService.getImagesByProject(projectId));
    }

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(ImageModel imageModel) {
        try {
            return imageService.uploadImage(imageModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
