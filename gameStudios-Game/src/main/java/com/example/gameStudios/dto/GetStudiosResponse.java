package com.example.gameStudios.dto;

import lombok.*;
import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStudiosResponse {

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @ToString
        @EqualsAndHashCode
        public static class Studio {
            private UUID id;
            private String name;
        }

        @Singular
        private List<Studio> studios;
}
