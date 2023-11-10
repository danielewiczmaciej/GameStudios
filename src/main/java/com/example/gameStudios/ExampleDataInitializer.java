package com.example.gameStudios;

import com.example.gameStudios.entity.Category;
import com.example.gameStudios.entity.Game;
import com.example.gameStudios.entity.Studio;
import com.example.gameStudios.service.CategoryService;
import com.example.gameStudios.service.GameService;
import com.example.gameStudios.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ExampleDataInitializer {

    private final GameService gameService;
    private final StudioService studioService;
    private final CategoryService categoryService;

    @Autowired
    public ExampleDataInitializer(GameService gameService, StudioService studioService, CategoryService categoryService) {
        this.gameService = gameService;
        this.studioService = studioService;
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void initializeExampleData() {
        // Create and populate example data with real game studios and games
        Category actionCategory = categoryService.createCategory("Action");
        Category rpgCategory = categoryService.createCategory("RPG");
        Category sportsCategory = categoryService.createCategory("Sports");
        // Game Studios
        Studio cdProjectRed = studioService.createStudio("CD Projekt Red", 2002);
        Studio rockstar = studioService.createStudio("Rockstar", 1998);
        Studio ubisoft = studioService.createStudio("Ubisoft", 1986);

        // Games
        Game witcher3 = gameService.createGame("The Witcher 3: Wild Hunt", 2015, cdProjectRed.getId(), actionCategory.getId());
        Game gtaV = gameService.createGame("Grand Theft Auto V", 2013, rockstar.getId(), actionCategory.getId());
        Game farCry5 = gameService.createGame("Far Cry 5", 2018, ubisoft.getId(), rpgCategory.getId());
    }
}
