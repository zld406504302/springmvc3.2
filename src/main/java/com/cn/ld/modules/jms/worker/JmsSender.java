package com.cn.ld.modules.jms.worker;

import java.util.Collection;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.Assert;

public class JmsSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private TaskExecutor taskExecutor;

	private Destination destination;

	private boolean isSendAsync = false;

	public JmsSender() {
		if (null == destination)
			destination = new ActiveMQTopic("topic");
	}

	public void sendSingle(String message) {
		sendMessage(message);
	}

	public void sendBatch(Collection<?> messages) {
		Assert.notNull(messages, "param 'messages' can't be null !");
		Assert.notEmpty(messages, "param 'message' can't be empty !");
		for (Object message : messages) {
			if (null != message && message instanceof String) {
				sendSingle(String.valueOf(message));
			}
		}
	}

	private void sendMessage(final String message) {
		if (isSendAsync) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					send(message);
				}
			});
		} else {
			send(message);
		}
	}

	private void send(final String message) {
		this.jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}

		});
	}

	public boolean isSendAsync() {
		return isSendAsync;
	}

	public void setSendAsync(boolean isSendAsync) {
		this.isSendAsync = isSendAsync;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}
