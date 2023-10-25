package com.example.gameStudios;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "studio")
public class Game implements Serializable, Comparable<Game> {
    private String name;
    private int releaseYear;
    private Studio studio;

    @Override
    public int compareTo(Game otherGame) {
        // Compare games based on their release year
        return Integer.compare(this.releaseYear, otherGame.releaseYear);
    }

    public static class GameBuilder {
        private Studio studio; // Add a field to hold the Studio reference

        // Method to set the Studio
        public GameBuilder studio(Studio studio) {
            this.studio = studio;
            return this;
        }

        public Game build() {
            Game game = new Game(this.name, this.releaseYear, this.studio);
            if (this.studio != null) {
                // Add the game to the Studio's list of games
                this.studio.getGames().add(game);
            }
            return game;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseYear);
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
