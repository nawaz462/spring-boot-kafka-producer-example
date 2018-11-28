package com.kafkaExample.springbootkafkaproducerexample.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface MessageStreams {

	String InBoundTopic1 = "InBoundTopic1";
	String OutBoundTopic1 = "OutBoundTopic1";
	String OutBoundTopic2 = "OutBoundTopic2";
	
	
	@Output(OutBoundTopic1)
	MessageChannel outBoundTopic1MessagePublisher();
	
	@Input(InBoundTopic1)
	SubscribableChannel inBoundTopic1MessageSubscriber();
	
	@Output(OutBoundTopic2)
	MessageChannel outBoundTopic2MessagePublisher();
	
	
}
