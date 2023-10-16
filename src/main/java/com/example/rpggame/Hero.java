package com.example.rpggame;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hero {
    private String name;
    private int level;
    private Set<Skill> skills;
}
