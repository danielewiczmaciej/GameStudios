package com.example.gameStudios.function;

import com.example.gameStudios.entity.Studio;
import com.example.gameStudios.entity.Category;
import org.springframework.stereotype.Component;
import com.example.gameStudios.dto.PutGameRequest;
import com.example.gameStudios.entity.Game;

import java.util.function.BiFunction;
import java.util.UUID;

@Component
public class RequestToGameFunction implements BiFunction<UUID, PutGameRequest, Game> {
    @Override
    public Game apply(UUID id, PutGameRequest putGameRequest) {
        return Game.builder()
                .id(id)
                .name(putGameRequest.getName())
                .releaseYear(putGameRequest.getReleaseYear())
                .studio(Studio.builder()
                        .id(putGameRequest.getStudioId())
                        .build())
                .category(Category.builder()
                        .id(putGameRequest.getCategoryId())
                        .build())
                .build();
    }
}
