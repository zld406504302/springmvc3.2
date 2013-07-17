package com.cn.ld.modules.jms.handler;

import java.io.Serializable;
import java.util.Map;

import javax.jms.TextMessage;

public interface MessageHandler {
	void receive(TextMessage message);

	void handleMessage(String message);

	void handleMessage(Map<String, Object> message);

	void handleMessage(byte[] message);

	void handleMessage(Serializable message);
}
