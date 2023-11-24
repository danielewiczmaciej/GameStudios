package com.example.gameStudios.service;

import com.example.gameStudios.dto.PatchGameRequest;
import com.example.gameStudios.entity.Category;
import com.example.gameStudios.entity.Game;
import com.example.gameStudios.entity.Studio;
import com.example.gameStudios.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Game> find(UUID id) {
        return gameRepository.findById(id);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public boolean deleteGame(String gameName) {
        Game game = (Game) gameRepository.findByName(gameName);

        if (game != null) {
            gameRepository.delete(game);
            return true; // Studio deleted successfully
        }

        return false; // Studio not found or could not be deleted
    }

    public boolean deleteGame(UUID gameId) {
        Game game = gameRepository.findById(gameId).orElse(null);

        if (game != null) {
            gameRepository.delete(game);
            return true; // Studio deleted successfully
        }

        return false; // Studio not found or could not be deleted
    }

    public List<Game> findByStudioId(UUID studioId) {
        return gameRepository.findAllByStudio(studioService.getStudioById(studioId));
    }

    public List<Game> findByCategoryId(UUID categoryId) {
        return gameRepository.findAllByCategory(categoryService.getCategoryById(categoryId));
    }

    public Game findById(UUID gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    public void createGame(Game apply) {
        gameRepository.save(apply);
        apply.getStudio().getGames().add(apply);
    }

    public void updateGame(UUID gameId, PatchGameRequest patchGameRequest) {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game != null) {
            game.setName(patchGameRequest.getName());
            game.setReleaseYear(patchGameRequest.getReleaseYear());
            gameRepository.save(game);
        }
    }
}