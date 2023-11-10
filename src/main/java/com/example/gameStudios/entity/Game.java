package com.example.gameStudios.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "studio")
public class Game implements Serializable, Comparable<Game> {
    @Column(name = "game_name")
    private String name;
    @Column(name = "release_year")
    private int releaseYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "game_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @Override
    public int compareTo(Game otherGame) {
        // Compare games based on their release year
        return Integer.compare(this.releaseYear, otherGame.releaseYear);
    }

    public Game(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Game(String name, int releaseYear, Studio studio, Category category) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.studio = studio;
        this.category = category;
    }

    public static class GameBuilder {
        private Studio studio; // Add a field to hold the Studio reference

        // Method to set the Studio
        public GameBuilder studio(Studio studio) {
            this.studio = studio;
            return this;
        }

        public Game build() {
            Game game = new Game(this.name, this.releaseYear, this.studio, this.category);
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
