package com.cn.ld.framework.web;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestMappingHandlerMappingInterceptor extends
		HandlerInterceptorAdapter {
private static final String VIEW_NAME_PREFIX_REDIRECT="redirect:";
	
	private static final String VIEW_NAME_PREFIX_FORWARD="forward:";
	
	private ConcurrentHashMap<Class<?>, String> controllerMappingCache=new ConcurrentHashMap<Class<?>, String>();
	
	private String requestUrlSuffix;
	
	public String getRequestUrlSuffix() {
		return requestUrlSuffix;
	}

	public void setRequestUrlSuffix(String requestUrlSuffix) {
		this.requestUrlSuffix = requestUrlSuffix;
	}
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		addContollerMappingToView(modelAndView, handler);
	}
	
	/**
	 * 为视图名添加控制器映射路径前缀
	 * @param mv
	 * @param handler
	 */
	protected void addContollerMappingToView(ModelAndView mv, Object handler)
	{
		if(mv == null)
			return;
		
		String vn=mv.getViewName();
		
		if(vn == null)
			return;
		
		//Spring会自动处理redirect和forward,只需要为其添加可能的后缀即可
		if(vn.startsWith(VIEW_NAME_PREFIX_REDIRECT) || vn.startsWith(VIEW_NAME_PREFIX_FORWARD))
		{
			if(this.requestUrlSuffix != null)
			{
				int qm=vn.indexOf('?');
				
				if(qm < 0)
					vn+=this.requestUrlSuffix;
				else
					vn=vn.substring(0, qm)+this.requestUrlSuffix+vn.substring(qm);
				
				mv.setViewName(vn);
			}
			
			return;
		}
		
		//以“/”开头的不做处理
		if(vn.indexOf('/') != 0)
		{
			String cm=getControllerMapping(handler);
			
			if(cm != null)
			{
				if(!cm.endsWith("/"))
					cm+="/";
				
				if(cm.startsWith("/"))
					cm=cm.substring(1);
				
				vn=cm+vn;
				mv.setViewName(vn);
			}
		}
	}
	
	/**
	 * 获取控制器的映射路径
	 * @param handler
	 * @return
	 */
	protected String getControllerMapping(Object handler)
	{
		HandlerMethod handlerMethod = (HandlerMethod) handler ;
		Class<?> hc=handlerMethod.getBean().getClass();
		
		String re=this.controllerMappingCache.get(hc);
		
		if(re == null)
		{
			RequestMapping mapping = AnnotationUtils.findAnnotation(hc, RequestMapping.class);
			
			if(mapping != null)
			{
				String[] mvs=mapping.value();
				
				re=mvs[0];
			}
			
			this.controllerMappingCache.putIfAbsent(hc, re);
		}
		
		return re;
	}
}
