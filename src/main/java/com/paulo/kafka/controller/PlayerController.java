package com.paulo.kafka.controller;

import com.paulo.kafka.domain.requests.CreatePlayerRequest;
import com.paulo.kafka.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/player")
@Slf4j
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlayer(@RequestBody CreatePlayerRequest createPlayerRequest) {
        log.info("New request to add player with details: {}", createPlayerRequest);

        playerService.addPlayer(createPlayerRequest);

        return ResponseEntity.ok().build();
    }
}
