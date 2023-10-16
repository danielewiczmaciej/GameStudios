package com.example.rpggame;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    private String name;
    private int damage;
}
