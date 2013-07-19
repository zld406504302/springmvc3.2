package com.cn.ld.modules.user.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ld.base.web.BaseController;
import com.cn.ld.modules.user.domain.User;
import com.cn.ld.modules.user.service.UserService;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
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
	public @ResponseBody DatatablesResponse<User> queryUser(@DatatablesParams DatatablesCriterias criterias) {
	   DataSet<User> dataSet = this.userService.findUsersByCriterias(criterias);
	   DatatablesResponse<User> userRes = DatatablesResponse.build(dataSet, criterias);
	   return userRes ;
	}
}
