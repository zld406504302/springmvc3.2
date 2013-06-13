package com.cn.ld.framework.web;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
@SuppressWarnings("deprecation")

/**
 * {@linkplain AnnotationMethodHandlerAdapter}扩展类，它将控制器的映射路径添加为视图名前缀，以简化控制器的视图处理代码。<br>
 * 比如，有如下的控制器类声明：
 * <pre>
 * 	&#064;Controller
 *	&#064;RequestMapping("/project/element/area")
 *	public class AreaController extends BaseController
 *	{
 *		&#064;RequestMapping("/forInsert")
 *		public String forInsert(ModelMap modelMap)
 *		{
 *			return "area_forInsert";
 *		}
 *	}
 * </pre>
 * 那么，在经过这个类处理后，视图名称将变为“<b>project/element/area/area_forInser</b>”。<br>
 * 另外需要注意的是：<br>
 * 它不会处理以“/”开头的视图名 ，而对于以“redirect:”和“forward:”开头的视图名，它仅会为其添加可能的扩展名(通过{@linkplain #setRequestUrlSuffix(String)}方法)。
 */
public class ControllerMappingAwareAnnotationMethodHandlerAdapter extends AnnotationMethodHandlerAdapter
{
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

	@Override
	protected ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		ModelAndView mv=super.invokeHandlerMethod(request, response, handler);
		
		addContollerMappingToView(mv, handler);
		
		return mv;
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
		Class<?> hc=handler.getClass();
		
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
