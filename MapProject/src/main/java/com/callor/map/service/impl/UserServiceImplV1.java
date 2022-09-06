package com.callor.map.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callor.map.model.AuthorityVO;
import com.callor.map.model.UserVO;
import com.callor.map.persistence.UserDao;
import com.callor.map.service.UserService;

@Service
public class UserServiceImplV1 implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void create_user_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_auth_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AuthorityVO> select_auths(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int role_insert(List<AuthorityVO> auths) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(UserVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
