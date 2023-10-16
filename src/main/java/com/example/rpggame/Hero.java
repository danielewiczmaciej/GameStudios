package com.example.rpggame;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hero implements Serializable {
    private String name;
    private int level;
    private Set<Skill> skills;
}
