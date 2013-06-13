package com.cn.ld.modules.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/login")
public class UserLoginController {
	@RequestMapping("goLogin")
	public String query() {
		return "loginForm";
	}
}
