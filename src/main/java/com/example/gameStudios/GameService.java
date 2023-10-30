package com.example.gameStudios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final StudioService studioService;

    private final CategoryService categoryService;

    @Autowired
    public GameService(GameRepository gameRepository, StudioService studioService, CategoryService categoryService) {
        this.gameRepository = gameRepository;
        this.studioService = studioService;
        this.categoryService = categoryService;
    }

    public Game createGame(String name, int releaseYear, UUID studioId, UUID categoryId) {
        // Get the studio by ID
        Studio studio = studioService.getStudioById(studioId);
        Category category = categoryService.getCategoryById(categoryId);

        if (studio != null) {
            Game game = new Game();
            game.setName(name);
            game.setReleaseYear(releaseYear);
            game.setStudio(studio);
            game.setCategory(category);

            // Save the game and update the studio's games collection
            game = gameRepository.save(game);

            // Add the game to the studio's games collection
            studio.getGames().add(game);

            return game;
        } else {
            // Studio not found, handle the error or return null
            return null;
        }
    }
}