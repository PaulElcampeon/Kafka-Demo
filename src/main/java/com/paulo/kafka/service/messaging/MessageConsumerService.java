package com.paulo.kafka.service.messaging;


import com.paulo.kafka.domain.events.GameAddedEvent;
import com.paulo.kafka.domain.events.PlayerAddedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumerService {

    @KafkaListener(topics = "topic.add-player-event.v1", groupId = "my-group-id", containerFactory = "kafkaListenerContainerFactory")
    public void addedPlayerEventListener(ConsumerRecord message) {
        log.info("Received message: {}", message);

        if (message.value() instanceof PlayerAddedEvent) {
            handlePlayerAddedEvent(message.value());

            return;
        }

        log.error("Unhandled message: {}", message);
    }

    @KafkaListener(topics = "topic.add-game-event.v1", groupId = "my-group-id", containerFactory = "kafkaListenerContainerFactory")
    public void addedGameEventListener(ConsumerRecord message) {
        log.info("Received message: {}", message);

        if (message.value() instanceof GameAddedEvent) {
            handleGameAddedEvent(message.value());
            return;
        }

        log.error("Unhandled message: {}", message);
    }

    public void handleMessage(ConsumerRecord consumerRecord) {
        log.info("Received message: {}", consumerRecord);

        if (consumerRecord.value() instanceof PlayerAddedEvent) {
            handlePlayerAddedEvent(consumerRecord.value());
        } else if (consumerRecord.value() instanceof GameAddedEvent) {
            handleGameAddedEvent(consumerRecord.value());
        }

        log.error("Unhandled message: {}", consumerRecord);
    }

    public void handlePlayerAddedEvent(Object object) {
        PlayerAddedEvent playerAddedEvent = (PlayerAddedEvent) object;

        log.info("Player with id: {} was added", playerAddedEvent.id());
    }

    public void handleGameAddedEvent(Object object) {
        GameAddedEvent gameAddedEvent = (GameAddedEvent) object;

        log.info("Game with id: {} was added", gameAddedEvent.id());
    }
}
