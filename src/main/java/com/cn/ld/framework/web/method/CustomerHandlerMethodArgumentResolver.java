package com.cn.ld.framework.web.method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;

public class CustomerHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		DatatablesParams annot = parameter.getParameterAnnotation(DatatablesParams.class);
		return ((annot != null) && (DatatablesCriterias.class.isAssignableFrom(parameter.getParameterType())));
	}

	@Override
	public Object resolveArgument(MethodParameter methodParam,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		DatatablesParams parameterAnnotation = (DatatablesParams) methodParam
				.getParameterAnnotation(DatatablesParams.class);
		if (parameterAnnotation != null) {
			HttpServletRequest request = (HttpServletRequest) webRequest
					.getNativeRequest();
			return DatatablesCriterias.getFromRequest(request);
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
	
}
