package com.example.gameStudios.repository;

import com.example.gameStudios.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    List<Game> findByName(String name);

    List<Game> findAllByReleaseYear(int releaseYear);

    List<Game> findAllByStudioId(UUID studioId);

    Optional<Game> findById(UUID gameId);


}