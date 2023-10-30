package com.example.gameStudios;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GameDTOService {
    private List<GameDTO> gameDTOList = new ArrayList<>();

    public GameDTO createGameDTO(String name, int releaseYear) {
        GameDTO gameDTO = new GameDTO(name, releaseYear);
        // You can optionally perform additional logic here if needed
        gameDTOList.add(gameDTO);
        return gameDTO;
    }

    public GameDTO getGameDTOById(UUID gameDTOId) {
        // Search for a GameDTO by its ID in the list
        return gameDTOList.stream()
                .filter(dto -> Objects.equals(dto.getId(), gameDTOId))
                .findFirst()
                .orElse(null);
    }
}
