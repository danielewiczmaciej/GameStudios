package com.example.rpggame;

import java.util.Objects;

public class GameDTO implements Comparable<GameDTO> {
    private String name;
    private int releaseYear;

    public GameDTO(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
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
                '}';
    }
}
