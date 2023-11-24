package com.example.gameStudios.repository;

import com.example.gameStudios.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String categoryName);

    Optional<Category> findById(UUID categoryId);
}
