package com.cn.ld.modules.jms.handler;

import java.io.Serializable;
import java.util.Map;

import javax.jms.TextMessage;

import com.cn.ld.modules.annotation.MethodMonitorCount;

public interface MessageHandler {
	void receive(TextMessage message);
	
	@MethodMonitorCount(10000)
	void handleMessage(String message);

	void handleMessage(Map<String, Object> message);

	void handleMessage(byte[] message);

	void handleMessage(Serializable message);
}
