package com.paulo.kafka.controller;

import com.paulo.kafka.Animal;
import com.paulo.kafka.Human;
import com.paulo.kafka.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/animal")
    public String sendMessageAnimal(@RequestParam("message") String message) {

        messageProducer.sendMessage("my-topic", new Animal(message, 2));
        return "Message sent: " + message;
    }

    @PostMapping("/human")
    public String sendMessageHuman(@RequestParam("message") String message) {

        messageProducer.sendMessage("my-topic-human", new Human(message, 2));
        return "Message sent: " + message;
    }
}
