package com.example.gameStudios.function;

import com.example.gameStudios.dto.PatchGameRequest;
import com.example.gameStudios.entity.Game;

import java.util.function.BiFunction;
public class UpdateGameWithRequestFunction implements BiFunction<Game, PatchGameRequest, Game>{
    @Override
    public Game apply(Game game, PatchGameRequest patchGameRequest) {
        return Game.builder()
                .id(game.getId())
                .name(patchGameRequest.getName())
                .releaseYear(patchGameRequest.getReleaseYear())
                .studio(game.getStudio())
                .category(game.getCategory())
                .build();
    }
}
