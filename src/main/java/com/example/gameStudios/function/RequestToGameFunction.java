package com.example.gameStudios.function;

import com.example.gameStudios.entity.Studio;
import com.example.gameStudios.entity.Category;
import com.example.gameStudios.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.gameStudios.dto.PutGameRequest;
import com.example.gameStudios.entity.Game;

import java.util.function.BiFunction;
import java.util.UUID;

import com.example.gameStudios.repository.CategoryRepository;

@Component
public class RequestToGameFunction implements BiFunction<UUID, PutGameRequest, Game> {

    private final StudioRepository studioRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public RequestToGameFunction(StudioRepository studioRepository, CategoryRepository categoryRepository) {
        this.studioRepository = studioRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Game apply(UUID id, PutGameRequest putGameRequest) {
        return Game.builder()
                .id(id)
                .name(putGameRequest.getName())
                .releaseYear(putGameRequest.getReleaseYear())
                .studio(checkIfStudioExists(putGameRequest.getStudioId()))
                .category(checkIfCategoryExists(putGameRequest.getCategoryId()))
                .build();
    }

    public Studio checkIfStudioExists(UUID studioId) {
        Studio studio = studioRepository.findById(studioId).orElse(null);
        if (studio == null) {
            studio = Studio.builder().id(studioId).build();
        }
        return studio;
    }

    public Category checkIfCategoryExists(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            category = Category.builder().id(categoryId).build();
        }
        return category;
    }
}
