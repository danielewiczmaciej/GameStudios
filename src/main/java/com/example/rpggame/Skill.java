package com.example.rpggame;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill implements Serializable {
    private String name;
    private int damage;
}
