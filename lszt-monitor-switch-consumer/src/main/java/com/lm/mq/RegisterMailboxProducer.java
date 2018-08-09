package com.lm.mq;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisterMailboxProducer{
		@Autowired
	 private JmsMessagingTemplate jmsMessagingTemplate;
		/**
		 * 封装mq生产消息
		 * @param destination
		 * @param json
		 */
	 public void snedMsg(Destination destination, String json ) {
		 jmsMessagingTemplate.convertAndSend(destination,json);
	 }
}
