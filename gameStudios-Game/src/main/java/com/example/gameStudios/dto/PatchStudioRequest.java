package com.example.gameStudios.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchStudioRequest {
    private String name;
    private int yearOfFoundation;
}
