package com.kafkaExample.springbootkafkaproducerexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaExample.springbootkafkaproducerexample.model.User;

@RestController
public class MessageController {
	
    @Autowired
    private MessagePublisherService messagePublisherService;

    @PostMapping("/publishJson")
    public String publishJsonMessage(@RequestBody User user) {
    	
    	System.out.println("Entered into method");
		return messagePublisherService.publishToInboundTopic(user); 
    	
    }
}
