package com.callor.food.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.callor.food.persistance.BBsDao;
import com.callor.food.persistance.UserDao;

@Service
public class BeanService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BBsDao bbsDao;
	
	// 자동실행 하기 (꼼수)
	@Bean
	public void create_table() {
		userDao.create_user_table();
		userDao.create_auth_table();
		bbsDao.create_bbs_table();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
