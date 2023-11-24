package com.example.gameStudios.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private UUID id;

    @Setter
    @Column(name = "name")
    private String name;


    public Category(String name) {
        this.name = name;;
    }

    public static class CategoryBuilder {
        public CategoryBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public Category build() {
            return new Category(this.id, this.name);
        }
    }
}
