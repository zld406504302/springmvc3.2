package com.cn.ld.modules.jms.worker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.cn.ld.modules.jms.handler.MessageHandler;

public class JmsReceiver implements MessageHandler {
	private Collection<String> collection;

	@Override
	public void receive(TextMessage message) {
		try {
			if (collection == null) {
				this.collection = new ArrayList<String>();
			}
			collection.add(message.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleMessage(String message) {
		/*
		 * if(collection == null){ this.collection = new ArrayList<String>(); }
		 * collection.add(message);
		 */
	}

	@Override
	public void handleMessage(Map<String, Object> message) {
		Set<String> keySet = message.keySet();
		Iterator<String> keys = keySet.iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			System.out.println(message.get(key));
		}

	}

	@Override
	public void handleMessage(byte[] message) {

	}

	@Override
	public void handleMessage(Serializable message) {
	}

	public Collection<String> getCollection() {
		return collection;
	}

	public void setCollection(Collection<String> collection) {
		this.collection = collection;
	}

}
