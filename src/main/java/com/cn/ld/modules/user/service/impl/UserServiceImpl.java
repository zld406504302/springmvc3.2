package com.cn.ld.modules.user.service.impl;

import java.util.Arrays;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.cn.ld.modules.user.domain.User;
import com.cn.ld.modules.user.service.UserService;

@EnableCaching
@Service
public class UserServiceImpl implements UserService {
	private User[] users = null;
	{
		users = new User[5];
		users[0] = new User("java小生0", "111111", 24, '男');
		users[1] = new User("java小生1", "222222", 24, '男');
		users[2] = new User("java小生2", "333333", 24, '男');
		users[3] = new User("java小生3", "444444", 24, '男');
		users[4] = new User("java小生4", "555555", 24, '女');
	}

	@Cacheable(value = "user" , condition="#id < 3" ,unless="#result.name == 'java小生4'")
	public User findById(int id) {
		System.out.println("--------findById start-----------");
		System.out.println("agrs{id:"+id+"}");
		User u = Arrays.asList(users).get(id);
		System.out.println("--------findById end-----------");
		return u;
	}

	/*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = new User(username, "111111", 25, '男');
		return user ;
	}*/
}
