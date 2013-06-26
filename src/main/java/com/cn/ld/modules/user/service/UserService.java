package com.cn.ld.modules.user.service;

import org.springframework.stereotype.Service;

import com.cn.ld.modules.user.domain.User;

@Service
public interface UserService {
	public User findById(int id) ;
}
