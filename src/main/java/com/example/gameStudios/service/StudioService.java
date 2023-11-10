package com.example.gameStudios.service;

import com.example.gameStudios.entity.Studio;
import com.example.gameStudios.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudioService {
    private final StudioRepository studioRepository;
    private final CategoryService categoryService;


    @Autowired
    public StudioService(StudioRepository studioRepository, CategoryService categoryService) {
        this.studioRepository = studioRepository;
        this.categoryService = categoryService;
    }

    public Studio createStudio(String name, int yearOfFoundation) {
        Studio studio = new Studio();
        studio.setName(name);
        studio.setYearOfFoundation(yearOfFoundation);

        // Save the studio
        studio = studioRepository.save(studio);

        return studio;
    }

    public Studio getStudioById(UUID studioId) {
        return studioRepository.findById(studioId).orElse(null);
    }

    public UUID getStudioIdByName(String studioName) {
        return studioRepository.findByName(studioName).getId();
    }

    public List<Studio> getAllStudios() {
        return studioRepository.findAll();
    }

    public boolean deleteStudio(String studioName) {
        Studio studio = (Studio) studioRepository.findByName(studioName);

        if (studio != null) {
            studioRepository.delete(studio);
            return true; // Studio deleted successfully
        }

        return false; // Studio not found or could not be deleted
    }
}