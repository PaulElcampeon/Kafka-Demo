package com.paulo.kafka.service;

import com.paulo.kafka.domain.Player;
import com.paulo.kafka.domain.events.PlayerAddedEvent;
import com.paulo.kafka.domain.requests.CreatePlayerRequest;
import com.paulo.kafka.repository.PlayerRepository;
import com.paulo.kafka.service.messaging.MessageProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final PlayerRepository repository;
    private final MessageProducerService messageProducerService;

    public Player addPlayer(CreatePlayerRequest createPlayerRequest) {

        Player player = createPlayer(createPlayerRequest);

        player = repository.save(player);

        log.info("New player added with id: {}", player.getId());

        messageProducerService.sendMessage(
                "topic.add-player-event.v1",
                new PlayerAddedEvent(player.getId()));

        log.info("Player added event sent with id: {}", player.getId());

        return player;
    }

    private Player createPlayer(CreatePlayerRequest createPlayerRequest) {
        Player player = new Player();
        player.setName(createPlayerRequest.name());
        player.setMood(createPlayerRequest.mood());

        return player;
    }
}
