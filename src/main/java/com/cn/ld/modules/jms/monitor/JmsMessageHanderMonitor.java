package com.cn.ld.modules.jms.monitor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;
import org.springframework.util.StopWatch;

import com.cn.ld.modules.annotation.MethodMonitorCount;

public class JmsMessageHanderMonitor extends AbstractMonitoringInterceptor {

	private static final long serialVersionUID = 1L;
	private ConcurrentHashMap<java.lang.reflect.Method, PerformanceSampl> methodPerfSampl = new ConcurrentHashMap<java.lang.reflect.Method, PerformanceSampl>();

	private int monitorCount = 1;

	public JmsMessageHanderMonitor(int monitorCount) {
		this.monitorCount = monitorCount;
	}

	public JmsMessageHanderMonitor(boolean useDynamicLogger) {
		setUseDynamicLogger(useDynamicLogger);
	}

	@Override
	protected Object invokeUnderTrace(MethodInvocation methodinvocation,
			Log logger) throws Throwable {
		ReflectiveMethodInvocation reflectMethod = (ReflectiveMethodInvocation) methodinvocation;
		Method m = reflectMethod.getMethod();
		Annotation[] annotations = m.getAnnotations();

		if (null == annotations || annotations.length == 0) {
			return methodinvocation.proceed();
		}

		PerformanceSampl sampl = null;
		// 将追踪的目标方法作为key缓存，如果已经存在，直接获取该方法的跟踪内容对象PerformanceSampl
		if (methodPerfSampl.containsKey(m)) {
			sampl = methodPerfSampl.get(m);
		} else {
			sampl = new PerformanceSampl();
			methodPerfSampl.put(m, sampl);
		}
		String target = m.toString();
		// 首次目标对象调用，初始化跟踪信息
		if (sampl.getReceNo() == 0) {

			StopWatch sw = new StopWatch(target);
			sampl.setStopWatch(sw);
			MethodMonitorCount mmc = (MethodMonitorCount) annotations[0];
			sampl.setMaxReceNo(mmc.value());
			sw.start(m.getName());
		}

		// 目标方法执行
		Object obj = methodinvocation.proceed();

		// 每次调用累加调用次数
		sampl.setReceNo(sampl.getReceNo() + 1);

		// 目标方法调用次数等于目标最大调用次数时，统计目标方法执行性能指标
		if (sampl.getReceNo() == sampl.getMaxReceNo()) {
			sampl.getStopWatch().stop();

			// 记录日志
			showTraceInfo(logger, sampl, target);

			// 追踪结束，将该方法的cache信息清空
			methodPerfSampl.remove(m);
		}

		return obj;
	}

	private void showTraceInfo(Log logger, PerformanceSampl sampl, String target) {
		String formatStr = "monitor target method:{0} ; expect execute times:{1};actual execute times:{2};execution Speed:{3}/s";
		MessageFormat paramMf = new MessageFormat(formatStr);
		long costTime = (sampl.getStopWatch().getTotalTimeMillis() / 1000);
		int executeTimes = sampl.getReceNo();
		costTime = costTime == 0 ? costTime : (executeTimes / costTime);
		logger.trace(paramMf.format(new Object[] { target,
				sampl.getMaxReceNo(), executeTimes, costTime }));
	}

	private static class PerformanceSampl {
		private StopWatch stopWatch;
		private int maxReceNo;
		// 接受数量
		private int receNo;

		public void setReceNo(int receNo) {
			this.receNo = receNo;
		}

		public StopWatch getStopWatch() {
			return stopWatch;
		}

		public void setStopWatch(StopWatch stopWatch) {
			this.stopWatch = stopWatch;
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

	public int getMonitorCount() {
		return monitorCount;
	}

	public void setMonitorCount(int monitorCount) {
		this.monitorCount = monitorCount;
	}
}
