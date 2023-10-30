package com.example.gameStudios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudioRepository extends JpaRepository<Studio, UUID> {
    Studio findByName(String name);
}
