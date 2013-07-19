package com.cn.ld.modules.user.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ld.base.web.BaseController;
import com.cn.ld.base.web.DataTableResult;
import com.cn.ld.modules.user.domain.User;
import com.cn.ld.modules.user.service.UserService;
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("findUserByName")
	@ResponseBody
	public User findUserByName(String name) {
		return userService.findByName(name);
	}

	@RequestMapping("save")
	public void save(User user) {
		this.userService.addUser(user);
	}

	@RequestMapping("remove")
	public void save(String name) {
		User u = this.userService.findByName(name);
		this.userService.removeUser(u);
	}
    
	@RequestMapping(value = "queryUser")
	@ResponseBody
	public Map<String, Object> queryUser() {
	   DataTableResult<User> dataTableResult = this.userService.findUsersByCriterias();
	   return toMapPagingResult(dataTableResult) ;
	}
}
