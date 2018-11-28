package com.kafkaExample.springbootkafkaproducerexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import com.kafkaExample.springbootkafkaproducerexample.config.MessageStreams;
import com.kafkaExample.springbootkafkaproducerexample.model.User;

@EnableBinding(MessageStreams.class)
public class MessagePublisherService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessagePublisherService.class);
	
	@Autowired
	@Qualifier(MessageStreams.OutBoundTopic1)
	private MessageChannel outBoundTopic1MessageSender;
	
	@Autowired
	@Qualifier(MessageStreams.OutBoundTopic2)
	private MessageChannel outBoundTopic2MessageSender;
	
	
	public String publishToInboundTopic(User user) {
		
		String PUBLISHER_RESPONSE = "";
		
		try {
			outBoundTopic1MessageSender.send(MessageBuilder.withPayload(user).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
			logger.info("Message Published successfully to topic ");
			PUBLISHER_RESPONSE = "Message Published successfully to topic";
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Message publishing to topic failed.");
			PUBLISHER_RESPONSE = "Message publishing to topic failed";
		}
		
		return PUBLISHER_RESPONSE;
	}
	
	public void publishToOutboundTopic(User user) {
			
		try {
			outBoundTopic2MessageSender.send(MessageBuilder.withPayload(user).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
			logger.info("Message Published successfully");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			logger.error(" Message publishing to topic failed.");
		}
			
	}
	
}
