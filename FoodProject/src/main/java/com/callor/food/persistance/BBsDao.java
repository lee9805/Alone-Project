package com.callor.food.persistance;

import java.util.List;

import com.callor.food.model.BBsVO;

public interface BBsDao extends GenericDao<BBsVO, Long>{

	public List<BBsVO> findByUsername(String username);
	public void create_bbs_table();
	
}
