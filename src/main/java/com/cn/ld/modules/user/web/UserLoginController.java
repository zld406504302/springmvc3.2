package com.cn.ld.modules.user.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ld.modules.user.domain.User;
import com.cn.ld.modules.user.service.UserService;
@Controller
@RequestMapping("/user")
public class UserLoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("home")
	public String goHome() {
		return "home";
	}

	@RequestMapping("findUserById")
	@ResponseBody
	public User findUserById(int id) {
		return userService.findById(id);
	}

}
