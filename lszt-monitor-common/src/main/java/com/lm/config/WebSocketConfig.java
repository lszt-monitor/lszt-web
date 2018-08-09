package com.lm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;




@Configuration
//注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	/**
	 * registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL。
	 * 注册一个端点 任何发布或订阅的时候需要连接此端点
	 * setAllowedOrigins 非必须， *代表允许其他域进行连接
	 * withSockJS 表示开始sockejs支持
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。 
		registry.addEndpoint("/endpoint-websocket").addInterceptors(new HttpHandShajeIntecepter()).setAllowedOrigins("*").withSockJS();
		System.out.println("建立基站");
	}
	/**
	 * 配置消息代理（中介）
	 * enableSimpleBroker 服务端推送给客户端的路径前缀
	 * setApplicationDestinationPrefixes 客户端发送数据给服务器的一个前缀
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
		registry.enableSimpleBroker("/topic","/chat");
		registry.setApplicationDestinationPrefixes("/app");
	}
	

}
