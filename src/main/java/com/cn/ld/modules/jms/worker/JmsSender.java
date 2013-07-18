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

import com.cn.ld.modules.annotation.MethodMonitorCount;

public class JmsSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private TaskExecutor taskExecutor;

	private Destination destination;

	private boolean isSendAsync = false;

	public JmsSender(){}
	public JmsSender(Destination destination) {
		if (null == destination)
			this.destination = new ActiveMQTopic("topic");
		else
			this.destination = destination;
	}

	@MethodMonitorCount(10000)
	public void sendSingle(String message,Destination destination) {
		sendMessage(message,destination);
	}

	public void sendBatch(Collection<?> messages,Destination destination) {
		Assert.notNull(messages, "param 'messages' can't be null !");
		Assert.notEmpty(messages, "param 'message' can't be empty !");
		for (Object message : messages) {
			if (null != message && message instanceof String) {
				sendSingle(String.valueOf(message),destination);
			}
		}
	}

	private void sendMessage(final String message,Destination destination) {
		final Destination sendDest = destination ;
		if (isSendAsync) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					send(message,sendDest);
				}
			});
		} else {
			send(message,destination);
		}
	}

	private void send(final String message,Destination destination) {
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

}
