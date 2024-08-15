package com.paulo.kafka.service;

import com.paulo.kafka.Animal;
import com.paulo.kafka.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    @Qualifier("animal")
    private KafkaTemplate<String, Animal> animalKafkaTemplate;

    @Autowired
    @Qualifier("human")
    private KafkaTemplate<String, Human> humanKafkaTemplate;
//    public void sendMessage(String topic, String message) {
//        kafkaTemplate.send(topic, message);
//    }

    public void sendMessage(String topic, Animal animal) {
        animalKafkaTemplate.send(topic, animal);
    }

    public void sendMessage(String topic, Human human) {
        humanKafkaTemplate.send(topic, human);
    }
}
