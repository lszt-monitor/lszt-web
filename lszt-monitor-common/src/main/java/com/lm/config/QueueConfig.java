package com.lm.config;



import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
@Configuration
public class QueueConfig {

	@Bean
	public JmsTemplate jmsTemlate(ActiveMQConnectionFactory activeMQConnectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDeliveryMode(2);// 进行持久化配置 1表示非持久化 2表示持久化
		jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
		jmsTemplate.setSessionAcknowledgeMode(4);// 客户签收模式 事务签收
		return jmsTemplate;
	}

	// 定义一个消息监听器连接工厂，这里定义的是点对点模式的监听器连接工厂
	@Bean(name="jmsQueueListListener")
	public DefaultJmsListenerContainerFactory jmsQueueListListenerContainerFactory(
			ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory);
		// 设置连接数
		factory.setConcurrency("1-10");
		// 重连间隔时间
		factory.setRecoveryInterval(1000L);
		factory.setSessionAcknowledgeMode(4);// 客户签收模式 事务签收
		return factory;

	}

}
