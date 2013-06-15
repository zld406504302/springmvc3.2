package com.cn.ld.modules.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ld.modules.user.domain.User;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	@RequestMapping("doLogin")
	public String query(Model model,User user) {
		model.addAttribute("user", user);
		return "home";
	}
}
