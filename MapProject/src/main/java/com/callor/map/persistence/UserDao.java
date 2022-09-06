package com.callor.map.persistence;

import java.util.List;

import com.callor.map.model.AuthorityVO;
import com.callor.map.model.UserVO;

public interface UserDao extends GenericDao<UserVO, String> {

	public void create_user_table();
	public void create_auth_table();
	
	public List<AuthorityVO> select_auths(String username);
	public int role_insert(List<AuthorityVO> auths);
	
}
