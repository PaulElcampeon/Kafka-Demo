package com.paulo.kafka.service;


import com.paulo.kafka.Animal;
import com.paulo.kafka.Human;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-group-id", containerFactory = "animalKafkaListenerContainerFactory")
    public void listen(Animal message) {
        System.out.println("Received message: " + message);
    }

    @KafkaListener(topics = "my-topic-human", groupId = "my-group-id", containerFactory = "humanKafkaListenerContainerFactory")
    public void listen(Human message) {
        System.out.println("Received message: " + message);
    }

}
