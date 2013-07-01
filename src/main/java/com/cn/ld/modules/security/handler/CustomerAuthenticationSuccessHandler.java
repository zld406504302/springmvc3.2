package com.cn.ld.modules.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**   
 * @filename: CustomerAuthenticationSuccessHandler   
 * @description: 成功验证后业务处理类
 * @author lida  
 * @date 2013-7-1 上午11:04:17      
 */
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth) throws IOException,
			ServletException {
		logger.debug(">>>>>>>Acegi Security successHandler start<<<<<< ");
		response.sendRedirect("main");
		logger.debug(">>>>>>>Acegi Security successHandler end<<<<<< ");
	}
	
}
