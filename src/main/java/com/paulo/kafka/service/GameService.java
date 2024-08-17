package com.paulo.kafka.service;

import com.paulo.kafka.domain.Game;
import com.paulo.kafka.domain.Player;
import com.paulo.kafka.domain.events.GameAddedEvent;
import com.paulo.kafka.domain.events.PlayerAddedEvent;
import com.paulo.kafka.domain.requests.AddGameRequest;
import com.paulo.kafka.domain.requests.CreatePlayerRequest;
import com.paulo.kafka.repository.GameRepository;
import com.paulo.kafka.service.messaging.MessageProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository repository;
    private final MessageProducerService messageProducerService;

    public Game addGame(AddGameRequest addGameRequest) {

        Game game = createGame(addGameRequest);

        game = repository.save(game);

        log.info("New game added with id: {}", game.getId());

        messageProducerService.sendMessage(
                "topic.add-game-event.v1",
                new GameAddedEvent(game.getId()));

        log.info("Player added event sent with id: {}", game.getId());

        return game;
    }

    private Game createGame(AddGameRequest addGameRequest) {
        Game game = new Game();
        game.setName(addGameRequest.name());
        game.setType(addGameRequest.gameType());

        return game;
    }
}
