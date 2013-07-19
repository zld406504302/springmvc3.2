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

import com.cn.ld.modules.jms.worker.JmsSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JmsTest {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private JmsSender jmsSender;

	private String destination;
	private int no = 10* 10000;
	private String message;

	@Before
	public void init() throws IOException {
		String filePath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath()
				+ "message.txt";
		message = FileUtil.readAsString(new File(filePath));
		this.destination = "asyncTopic";
		this.jmsSender.setSendAsync(true);
	}

	@Test
	public void send() throws InterruptedException {
		ActiveMQTopic dest = new ActiveMQTopic(this.destination);
		for (int i = 0; i < no; i++) {
			jmsSender.sendSingle(message, dest);
		}
		Thread.sleep(1000000000);
	}

}
