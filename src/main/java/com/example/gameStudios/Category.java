package com.example.gameStudios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    @Getter
    private UUID id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

}
