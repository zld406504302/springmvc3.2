package com.cn.ld.modules.user.domain;

import org.springframework.security.core.context.SecurityContextHolder;
public class LoginUserDetails extends User {

	// @Fields serialVersionUID : TODO 
	
	private static final long serialVersionUID = 1L;

	public static User getLoginUser() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
        User user = null ;
        
		if (principal instanceof User) {
			return ((User) principal);
		}
		else if(principal instanceof org.springframework.security.core.userdetails.User)
		{
			org.springframework.security.core.userdetails.User vu =(org.springframework.security.core.userdetails.User)principal;
			user=new User();
			user.setName(vu.getUsername());
			user.setPassword(vu.getPassword());
			user.setAge(25);
		}
		return user;
	}
}
