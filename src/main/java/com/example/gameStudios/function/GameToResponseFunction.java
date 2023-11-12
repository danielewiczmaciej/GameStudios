package com.example.gameStudios.function;

import org.springframework.stereotype.Component;
import com.example.gameStudios.dto.GetGameResponse;
import com.example.gameStudios.entity.Game;

import java.util.function.Function;
@Component
public class GameToResponseFunction implements Function<Game, GetGameResponse>{

    public GetGameResponse apply(Game game) {
        return GetGameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .releaseYear(game.getReleaseYear())
                .studioId(game.getStudioId())
                .categoryId(game.getCategoryId())
                .build();
    }
}
