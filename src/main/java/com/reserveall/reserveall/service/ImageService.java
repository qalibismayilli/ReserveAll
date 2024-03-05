package com.reserveall.reserveall.service;

import com.reserveall.reserveall.model.Image;
import com.reserveall.reserveall.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String add(String imageUrl){
//        Image image = new Image().
        return null;
    }
}
