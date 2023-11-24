package com.example.gameStudios.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.gameStudios.dto.GetStudioResponse;
import com.example.gameStudios.dto.GetStudiosResponse;

import java.util.UUID;

public interface StudioController {

    @GetMapping("/api/studios")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetStudiosResponse getAllStudios();

    @GetMapping("/api/studios/{studioId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetStudioResponse getStudio(@PathVariable("studioId") UUID studioId);

    @DeleteMapping("/api/studios/{studioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteStudio(@PathVariable("studioId") UUID studioId);
}
