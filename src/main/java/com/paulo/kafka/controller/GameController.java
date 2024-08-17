package com.paulo.kafka.controller;

import com.paulo.kafka.domain.requests.AddGameRequest;
import com.paulo.kafka.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
@Slf4j
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/add")
    public ResponseEntity<?> addGame(@RequestBody AddGameRequest addGameRequest) {
        log.info("New request to add game with details: {}", addGameRequest);

        gameService.addGame(addGameRequest);

        return ResponseEntity.ok().build();
    }
}
