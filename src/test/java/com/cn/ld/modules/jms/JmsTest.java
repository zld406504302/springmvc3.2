package com.cn.ld.modules.jms;

import java.io.File;
import java.io.IOException;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.ld.modules.jms.monitor.JmsMessageHanderMonitor;
import com.cn.ld.modules.jms.worker.JmsSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JmsTest {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private JmsMessageHanderMonitor jmsMessageHanderMonitor;

	@Autowired
	private JmsSender jmsSender;

	private String destination;
	private int no = 1 * 10000;
	private String message;

	@Before
	public void init() throws IOException {
		String filePath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath()
				+ "message.txt";
		message = FileUtil.readAsString(new File(filePath));
		this.destination = "asyncTopic";
		this.jmsSender.setDestination(new ActiveMQTopic(this.destination));
		this.jmsMessageHanderMonitor.setMaxReceNo(no);
		this.jmsMessageHanderMonitor.setDestnationName(this.destination);
	}

	@Test
	public void send() throws InterruptedException {
		for (int i = 0; i < no; i++) {
			jmsSender.sendSingle(message);
		}
		Thread.sleep(1000000000);
	}

}
