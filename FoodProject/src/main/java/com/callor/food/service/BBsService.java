package com.callor.food.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.food.model.BBsVO;
import com.callor.food.persistance.BBsDao;

public interface BBsService extends BBsDao {

	public String insertBbsAndFile(BBsVO bbsVO, MultipartFile file);
	public String insertBbsAndFiles(BBsVO bbsVO, MultipartHttpServletRequest files);
	
}
