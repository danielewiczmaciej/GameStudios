package com.example.gameStudios.repository;

import com.example.gameStudios.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudioRepository extends JpaRepository<Studio, UUID> {

    Studio findByName(String name);

    Studio findByYearOfFoundation(int yearOfFoundation);

    Optional<Studio> findById(UUID studioId);
}
