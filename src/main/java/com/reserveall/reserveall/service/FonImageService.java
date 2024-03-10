package com.reserveall.reserveall.service;

import com.reserveall.reserveall.model.FonImage;
import com.reserveall.reserveall.repository.FonImageRepository;
import org.springframework.stereotype.Service;

@Service
public class FonImageService {
    private final FonImageRepository fonImageRepository;

    public FonImageService(FonImageRepository fonImageRepository) {
        this.fonImageRepository = fonImageRepository;
    }

    public String addFonImage(String fonImageUrl){
//        FonImage image = fonImageRepository.save(fonImageUrl);
        return null;
    }
}
