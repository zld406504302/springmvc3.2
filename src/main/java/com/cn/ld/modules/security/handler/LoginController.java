package com.cn.ld.modules.security.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login.html")
	public String login() {
		return "login/login";
	}

	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}
	
	@RequestMapping("main")
	public String goMain(Model model) {
		model.addAttribute("loginError", true);
		return "main";
	}
}
