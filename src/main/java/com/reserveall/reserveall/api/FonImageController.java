package com.reserveall.reserveall.api;

import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.service.FonImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fonImage")
public class FonImageController {

    private final FonImageService fonImageService;

    public FonImageController(FonImageService fonImageService) {
        this.fonImageService = fonImageService;
    }

    @PostMapping("/addFonImage")
    public ResponseEntity<ImageResponseDto> addFonImage(@RequestParam String fonImageUrl){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fonImageService.addFonImage(fonImageUrl));
    }

    @PostMapping("/removeFonImage")
    public ResponseEntity<ImageResponseDto> removeFonImage(@RequestParam String imageId){
        return ResponseEntity.ok(fonImageService.removeFonImage(imageId));
    }

    @GetMapping("getFonImageById")
    public ResponseEntity<ImageResponseDto> getFonImageById(String imageId){
        return ResponseEntity.ok(fonImageService.getFonImageById(imageId));
    }

    @GetMapping("getAllFonImages")
    public ResponseEntity<List<ImageResponseDto>> getAllFonImages(){
        return ResponseEntity.ok(fonImageService.getAllFonImages());
    }

}
