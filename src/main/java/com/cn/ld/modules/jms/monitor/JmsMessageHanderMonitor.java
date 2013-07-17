package com.cn.ld.modules.jms.monitor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;
import org.springframework.util.StopWatch;

public class JmsMessageHanderMonitor extends AbstractMonitoringInterceptor {

	private static final long serialVersionUID = 1L;
	private StopWatch stopWatch;
	private String destnationName;
	private int maxReceNo;
	// 接受数量
	private int receNo;

	public JmsMessageHanderMonitor(String destnationName) {
		this.destnationName = destnationName;
		stopWatch = new StopWatch(destnationName);
	}

	public JmsMessageHanderMonitor(boolean useDynamicLogger) {
		setUseDynamicLogger(useDynamicLogger);
	}

	@Override
	protected Object invokeUnderTrace(MethodInvocation methodinvocation,
			Log logger) throws Throwable {
		if (this.receNo == 0) {
			logger.trace("-------------消息接收性能跟踪开始-------------");
			logger.trace(">>>>>>>消息发送总量:" + this.maxReceNo);
			stopWatch.start(this.destnationName);
		}
		this.receNo++;
		Object obj = methodinvocation.proceed();
		if (this.receNo == maxReceNo) {
			stopWatch.stop();
			logger.trace(">>>>>>>实际接受消息数量:" + this.receNo);
			long costTime = (stopWatch.getTotalTimeMillis() / 1000);
			logger.trace(">>>>>>>消耗总时间为:" + costTime + " 秒");
			logger.trace(">>>>>>>每秒发送量:" + (this.receNo / costTime));
			logger.trace("-------------消息接收性能跟踪结束-------------");
		}
		return obj;
	}

	public void setReceNo(int receNo) {
		this.receNo = receNo;
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(StopWatch stopWatch) {
		this.stopWatch = stopWatch;
	}

	public String getDestnationName() {
		return destnationName;
	}

	public void setDestnationName(String destnationName) {
		this.destnationName = destnationName;
	}

	public int getMaxReceNo() {
		return maxReceNo;
	}

	public void setMaxReceNo(int maxReceNo) {
		this.maxReceNo = maxReceNo;
	}

	public int getReceNo() {
		return receNo;
	}

}
