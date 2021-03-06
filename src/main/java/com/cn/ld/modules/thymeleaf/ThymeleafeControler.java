package com.cn.ld.modules.thymeleaf;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ld.base.web.BaseController;
import com.cn.ld.modules.user.domain.User;
import com.cn.ld.modules.user.service.UserService;

@RequestMapping("/thymeleafe/")
@Controller
public class ThymeleafeControler extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("userList")
	public String goSerchUserH(HttpServletResponse response) {
		return "userList";
	}
	
	@RequestMapping("findUserByName")
	@ResponseBody
	public User findUserByName(String name) {
		return userService.findByName(name);
	}
	
	@ModelAttribute("allUsers")
	public List<User> getAllUsers(){
		return this.userService.getUserList();
	}
	
	@RequestMapping("save")
	public void save(User user){
		this.userService.addUser(user);
	}
	
	@RequestMapping("remove")
	public void save(String name){
		User u = this.userService.findByName(name);
		this.userService.removeUser(u);
	}
}
