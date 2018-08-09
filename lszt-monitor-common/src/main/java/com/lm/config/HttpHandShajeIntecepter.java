package com.lm.config;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * Http握手拦截器 可以通过这个方法获取 request,和 response
 * @author Asus
 *
 */
public class HttpHandShajeIntecepter implements HandshakeInterceptor{
		/**
		 * 握手之前
		 */
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
			if(request instanceof ServletServerHttpRequest){
				ServletServerHttpRequest servletRequest=(ServletServerHttpRequest)request;
				HttpSession session=servletRequest.getServletRequest().getSession();
			String sessionId=session.getId();
			System.out.println("握手之前beforeHandshake====================="+sessionId);
			attributes.put("sessionId", sessionId);
			}
					return true;
	}
	/**
	 * 握手之后
	 */
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		if(request instanceof ServletServerHttpRequest){
			ServletServerHttpRequest servletRequest=(ServletServerHttpRequest)request;
			HttpSession session=servletRequest.getServletRequest().getSession();
		String sessionId=session.getId();
		System.out.println("握手之后afterHandshake====================="+sessionId);
		}
	}
}
