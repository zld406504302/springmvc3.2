package com.cn.ld.modules.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @filename: LoginController   
 * @description: 登陆控制器
 * @author lida  
 * @date 2013-7-1 上午11:30:17      
 */
@Controller
public class LoginController {
	@RequestMapping("main")
	public String goHome() {

		return "main";
	}
}
