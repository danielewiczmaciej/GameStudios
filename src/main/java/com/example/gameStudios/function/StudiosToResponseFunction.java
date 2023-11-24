package com.example.gameStudios.function;

import org.springframework.stereotype.Component;
import com.example.gameStudios.dto.GetStudiosResponse;
import com.example.gameStudios.entity.Studio;

import java.util.List;
import java.util.function.Function;

@Component
public class StudiosToResponseFunction implements Function<List<Studio>, GetStudiosResponse> {
    @Override
    public GetStudiosResponse apply(List<Studio> studios) {
        return GetStudiosResponse.builder()
                .studios(studios.stream()
                        .map(studio -> GetStudiosResponse.Studio.builder()
                                .id(studio.getId())
                                .name(studio.getName())
                                .build())
                        .toList())
                .build();
    }
}
