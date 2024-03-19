package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.response.ImageResponseDto;
import com.reserveall.reserveall.exception.GenericException;
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
        FonImage fromDb = fonImageRepository.save(new FonImage(fonImageUrl));
        return convertToImageResponseDto(fromDb);
    }

    @Transactional
    public ImageResponseDto removeFonImage(String imageId){
        FonImage image = fonImageRepository.findById(imageId)
                .orElseThrow(() -> new GenericException("Fon image not found by id: " + imageId));
        fonImageRepository.delete(image);
        return convertToImageResponseDto(image);
    }

    public ImageResponseDto getFonImageById(String imageId){
        return convertToImageResponseDto(fonImageRepository.findById(imageId)
                .orElseThrow(()->new GenericException("Fon image not found by id: " + imageId)));
    }

    public List<ImageResponseDto> getAllFonImages(){
        return fonImageRepository.findAll().stream().map(i -> convertToImageResponseDto(i)).toList();
    }
}
