package com.example.gameStudios.controller.impl;

import com.example.gameStudios.dto.PatchGameRequest;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.example.gameStudios.controller.api.GameController;
import com.example.gameStudios.dto.GetGameResponse;
import com.example.gameStudios.dto.GetGamesResponse;
import com.example.gameStudios.dto.PutGameRequest;
import com.example.gameStudios.entity.Game;
import com.example.gameStudios.function.RequestToGameFunction;
import com.example.gameStudios.function.GameToResponseFunction;
import com.example.gameStudios.function.GamesToResponseFunction;
import com.example.gameStudios.service.GameService;

import java.util.UUID;
import java.util.List;
import java.util.logging.Level;
import java.io.IOException;

@RestController
@Log
public class GameDefaultController implements GameController{
    private final GameService gameService;

    private final RequestToGameFunction requestToGameFunction;

    private final GameToResponseFunction gameToResponseFunction;

    private final GamesToResponseFunction gamesToResponseFunction;

    @Autowired
    public GameDefaultController(GameService gameService, RequestToGameFunction requestToGameFunction, GameToResponseFunction gameToResponseFunction, GamesToResponseFunction gamesToResponseFunction) {
        this.gameService = gameService;
        this.requestToGameFunction = requestToGameFunction;
        this.gameToResponseFunction = gameToResponseFunction;
        this.gamesToResponseFunction = gamesToResponseFunction;
    }

    @Override
    public GetGamesResponse getAllGames() {
        List<Game> games = gameService.findAll();
        return gamesToResponseFunction.apply(games);
    }

    @Override
    public GetGamesResponse getGamesByStudioId(UUID studioId) {
        List<Game> games = gameService.findByStudioId(studioId);
        return gamesToResponseFunction.apply(games);
    }

    @Override
    public GetGamesResponse getGamesByCategoryId(UUID categoryId) {
        List<Game> games = gameService.findByCategoryId(categoryId);
        return gamesToResponseFunction.apply(games);
    }


    @Override
    public GetGameResponse getGame(UUID gameId) {
        Game game = gameService.findById(gameId);
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return gameToResponseFunction.apply(game);
    }

    @Override
    public void putGame(UUID gameId, PutGameRequest putGameRequest) {
        gameService.createGame(requestToGameFunction.apply(gameId, putGameRequest));
    }

    @Override
    public void patchGame(UUID gameId, PatchGameRequest patchGameRequest) {
        gameService.find(gameId).ifPresentOrElse(
                game -> {
                    game.setName(patchGameRequest.getName());
                    game.setReleaseYear(patchGameRequest.getReleaseYear());
                    gameService.createGame(game);
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
                }
            );
    }

    @Override
    public void deleteGame(UUID gameId) {
        gameService.find(gameId).ifPresentOrElse(
                game -> gameService.deleteGame(gameId),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
                }
            );
    }


}
