package com.kafkaExample.springbootkafkaproducerexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kafkaExample.springbootkafkaproducerexample.config.MessageStreams;
import com.kafkaExample.springbootkafkaproducerexample.model.User;

@Component
public class MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

	@Autowired
    private MessagePublisherService messagePublisherService;
	
	@StreamListener(MessageStreams.InBoundTopic1)
    public void handleGreetings(@Payload User userInfo) {
		
		logger.info("Consumed JSON Message : "+ userInfo);
		
		//Processing - Updating the name in input JSON
		userInfo.setName(userInfo.getName()+"_Updated");
		
		try {
			//Publishing the updated information to another topic
			messagePublisherService.publishToOutboundTopic(userInfo);
			logger.info("Updated message has been published successfully to topic");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Updated message publishing failed.");
		}
		
    }
	
	
	
}
