package com.callor.food.persistance;

import java.util.List;

import com.callor.food.model.AuthorVO;
import com.callor.food.model.UserVO;

public interface UserDao extends GenericDao<UserVO, String>{
	
	public void create_user_table();
	public void create_auth_table();
	public List<AuthorVO> select_auths(String username);
	public int role_insert(List<AuthorVO> auths);

}
