package com.example.gameStudios.function;

import org.springframework.stereotype.Component;
import com.example.gameStudios.dto.GetGamesResponse;
import com.example.gameStudios.entity.Game;

import java.util.List;
import java.util.function.Function;

@Component
public class GamesToResponseFunction implements Function<List<Game>, GetGamesResponse>{

    public GetGamesResponse apply(List<Game> games) {
        return GetGamesResponse.builder()
                .games(games.stream()
                        .map(game -> GetGamesResponse.Game.builder()
                                .id(game.getId())
                                .name(game.getName())
                                .build())
                        .toList())
                .build();
    }
}
