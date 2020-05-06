package com.rabbitmq.websocket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppUtil implements ApplicationContextAware {

	public static ApplicationContext applicationContext;

	/**
	 * spring启动时注入context
	 */
	public void setApplicationContext(ApplicationContext contex) {
		AppUtil.applicationContext = contex;
	}
}
