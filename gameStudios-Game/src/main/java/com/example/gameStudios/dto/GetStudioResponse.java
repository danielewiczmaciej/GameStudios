package com.example.gameStudios.dto;

import lombok.*;

import java.util.UUID;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStudioResponse {

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @ToString
        @EqualsAndHashCode
        public static class Game {
            private UUID id;
            private String name;
        }

        private UUID id;

        private String name;

        @Singular
        private Map<UUID, String> studios;
}
