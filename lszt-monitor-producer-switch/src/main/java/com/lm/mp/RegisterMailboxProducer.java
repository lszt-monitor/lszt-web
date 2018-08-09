package com.lm.mp;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisterMailboxProducer {
	/**
	 * mq模板引擎
	 */
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	/**
	 * 封装mq生产消息
	 * 
	 * @param destination
	 * @param json
	 */
	public void mqSnedMsg(Destination destination, String json) {
		jmsMessagingTemplate.convertAndSend(destination, json);
	}

	/**
	 * 封装发送json数据类型
	 * 
	 * @param json
	 */
	public void senMsg(String json, String quene) {
		ActiveMQQueue activeMQQueue = new ActiveMQQueue(quene);
		mqSnedMsg(activeMQQueue, json);
	}

}
