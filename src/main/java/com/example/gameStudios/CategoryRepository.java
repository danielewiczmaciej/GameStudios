package com.example.gameStudios;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String categoryName);
}
