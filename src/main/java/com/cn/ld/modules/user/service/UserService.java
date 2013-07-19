package com.cn.ld.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cn.ld.base.web.DataTableResult;
import com.cn.ld.modules.user.domain.User;

@Service
public interface UserService {
	public User findByName(String name) ;
	
	public void addUser(User user) ;
	
	public List<User> getUserList();
	
	public void removeUser(User u);
	
	public DataTableResult<User> findUsersByCriterias();
	
}
