package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.model.FonImage;
import com.reserveall.reserveall.repository.FonImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonImageService {
    private final FonImageRepository fonImageRepository;

    public FonImageService(FonImageRepository fonImageRepository) {
        this.fonImageRepository = fonImageRepository;
    }

    private static ImageResponseDto convertToImageResponseDto(FonImage image){
        return new ImageResponseDto(image.getId(), image.getUrl());
    }


    @Transactional
    public ImageResponseDto addFonImage(String fonImageUrl) {
        FonImage image = new FonImage(fonImageUrl);
        return convertToImageResponseDto(image);
    }

    @Transactional
    public ImageResponseDto removeFonImage(String imageId){
        FonImage image = fonImageRepository.findById(imageId).orElseThrow();
        fonImageRepository.delete(image);
        return convertToImageResponseDto(image);
    }

    public ImageResponseDto getFonImageById(String imageId){
        return convertToImageResponseDto(fonImageRepository.findById(imageId).orElseThrow());
    }

    public List<ImageResponseDto> getAllFonImages(){
        return fonImageRepository.findAll().stream().map(i -> convertToImageResponseDto(i)).toList();
    }
}
