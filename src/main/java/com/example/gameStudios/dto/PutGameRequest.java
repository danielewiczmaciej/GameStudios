package com.example.gameStudios.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutGameRequest {
    private String name;
    private int releaseYear;
    private UUID studioId;
    private UUID categoryId;

}
