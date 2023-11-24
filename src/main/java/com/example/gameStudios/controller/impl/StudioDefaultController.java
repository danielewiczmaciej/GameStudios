package com.example.gameStudios.controller.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.gameStudios.controller.api.StudioController;
import com.example.gameStudios.dto.GetStudioResponse;
import com.example.gameStudios.dto.GetStudiosResponse;
import com.example.gameStudios.entity.Studio;
import com.example.gameStudios.service.StudioService;
import com.example.gameStudios.function.StudioToResponseFunction;
import com.example.gameStudios.function.StudiosToResponseFunction;

import java.util.UUID;
import java.util.List;

@RestController
@Log
public class StudioDefaultController implements StudioController{

        private final StudioService studioService;
        private final StudioToResponseFunction studioToResponseFunction;
        private final StudiosToResponseFunction studiosToResponseFunction;

        @Autowired
        public StudioDefaultController(StudioService studioService, StudioToResponseFunction studioToResponseFunction, StudiosToResponseFunction studiosToResponseFunction) {
            this.studioService = studioService;
            this.studioToResponseFunction = studioToResponseFunction;
            this.studiosToResponseFunction = studiosToResponseFunction;
        }

        @Override
        public GetStudiosResponse getAllStudios() {
            List<Studio> studios = studioService.findAll();
            return studiosToResponseFunction.apply(studios);
        }

        @Override
        public GetStudioResponse getStudio(UUID studioId) {
            Studio studio = studioService.getStudioById(studioId);
            if (studio == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studio not found");
            }
            return studioToResponseFunction.apply(studio);
        }

        @Override
        public void deleteStudio(UUID studioId) {
            if (!studioService.deleteStudio(studioId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studio not found");
            }
        }
}
