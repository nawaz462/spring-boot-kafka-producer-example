package com.kafkaExample.springbootkafkaproducerexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaExample.springbootkafkaproducerexample.model.User;

@RestController
public class MessageController {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Json_Example";

    @GetMapping("/publish/{message}")
    public String post(@PathVariable("message") final String message) {

//    	kafkaTemplate.send(TOPIC, message);
        kafkaTemplate.send(TOPIC, new User(message, "sample", 100L));

        return "Published successfully";
    }
}
