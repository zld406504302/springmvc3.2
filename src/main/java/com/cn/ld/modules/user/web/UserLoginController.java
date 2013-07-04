package com.cn.ld.modules.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ld.base.web.BaseController;
import com.cn.ld.modules.user.domain.User;
import com.cn.ld.modules.user.service.UserService;
@Controller
@RequestMapping("/user")
public class UserLoginController extends BaseController{

	@Autowired
	private UserService userService;

	@RequestMapping("goSerchUser")
	public String goSerchUser(){
		return "search";
	}
	
	@RequestMapping("findUserByName")
	@ResponseBody
	public User findUserByName(String name) {
		return userService.findByName(name);
	}

}
