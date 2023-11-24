package com.example.gameStudios.function;

import org.springframework.stereotype.Component;
import com.example.gameStudios.dto.GetStudioResponse;
import com.example.gameStudios.entity.Studio;

import java.util.function.Function;

@Component
public class StudioToResponseFunction implements Function<Studio, GetStudioResponse>{
    public GetStudioResponse apply(Studio studio) {
        return GetStudioResponse.builder()
                .id(studio.getId())
                .name(studio.getName())
                .build();
    }
}
