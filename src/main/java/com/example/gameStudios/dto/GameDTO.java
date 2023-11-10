package com.example.gameStudios.dto;

import com.example.gameStudios.entity.Category;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

public class GameDTO implements Comparable<GameDTO> {
    @Getter
    private String name;
    @Getter
    private int releaseYear;

    private Category category;
    private UUID id;

    public GameDTO(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    @Override
    public int compareTo(GameDTO otherGameDTO) {
        // Compare GameDTO objects based on release year
        return Integer.compare(this.releaseYear, otherGameDTO.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseYear);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameDTO gameDTO = (GameDTO) obj;
        return releaseYear == gameDTO.releaseYear && Objects.equals(name, gameDTO.name);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", category=" + category +
                '}';
    }

    public Object getId() {
        return null;
    }
}
