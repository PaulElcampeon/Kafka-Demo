package com.paulo.kafka.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessageProducerService {

    @Autowired
    @Qualifier("messageProducer")
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void sendMessage(String topic, Object message) {
         CompletableFuture.runAsync(() -> kafkaTemplate.send(topic, message));
    }
}
