package com.example.gameStudios.controller.api;

import com.example.gameStudios.dto.PatchGameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.gameStudios.dto.GetGameResponse;
import com.example.gameStudios.dto.GetGamesResponse;
import com.example.gameStudios.dto.PutGameRequest;

import java.util.UUID;
public interface GameController {


    @GetMapping("/api/games")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGamesResponse getAllGames();

    @GetMapping ("/api/studios/{studioId}/games")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGamesResponse getGamesByStudioId(@PathVariable("studioId") UUID studioId);

    @GetMapping("/api/categories/{categoryId}/games")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGamesResponse getGamesByCategoryId(@PathVariable("categoryId") UUID categoryId);

    @GetMapping("/api/games/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGameResponse getGame(@PathVariable("gameId") UUID gameId);

    @PutMapping("/api/games/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    void putGame(@PathVariable("gameId") UUID gameId, @RequestBody PutGameRequest putGameRequest);

    @PatchMapping("/api/games/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchGame(@PathVariable("gameId") UUID gameId, @RequestBody PatchGameRequest patchGameRequest);

    @DeleteMapping("/api/games/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGame(@PathVariable("gameId") UUID gameId);

}
