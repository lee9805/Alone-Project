package com.callor.food.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.food.model.BBsVO;
import com.callor.food.model.FilesVO;
import com.callor.food.persistance.BBsDao;
import com.callor.food.persistance.FileDao;
import com.callor.food.service.BBsService;
import com.callor.food.service.FileUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BBsServiceImplV1 implements BBsService {

	@Autowired
	private BBsDao bbsDao;

	@Autowired
	private FileUpService fileService;
	
	@Autowired
	private FileDao  fileDao;
	
	

	@Override
	public String insertBbsAndFile(BBsVO bbsVO, MultipartFile file) {
		
		// 게시판 내용(text 들)을 먼저 insert 실행
		int ret = bbsDao.insert(bbsVO);
		
		// 게시판 내용을 insert 하고 나면
		// bbsVO.b_seq 값이 새로 생성된 상태가 된다
		
		log.debug("INSERT {}",ret);
		
		// 정상적으로 BBS 내용이 insert 되었으면
		if(ret > 0) {
			try {
				// 파일 업로드 하기
				String fileName = fileService.fileUp(file);
				
				log.debug("업로드한 파일 {}", fileName);
				
				// 업로드된 파일정보로 imageVO 데이터 생성하기
				FilesVO imageVO = FilesVO.builder()
						.i_originalName(file.getOriginalFilename())
						.i_imageName(fileName)
						.i_bseq(bbsVO.getB_seq())
						.build();
				
				// tbl_images 에 추가하기
				fileDao.insert(imageVO);
				log.debug(imageVO.toString());
				return imageVO.getI_imageName();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.debug("FILE UP FAIL");
				return "FILE UP FAIL";
			}
		}
		
		return null;
	}

	@Override
	public String insertBbsAndFiles(BBsVO bbsVO, MultipartHttpServletRequest files) {
		
		int ret = bbsDao.insert(bbsVO);
		
		try {
			List<FilesVO> fileNames = fileService.filesUp(files);
			for(FilesVO file : fileNames) {
				file.setI_bseq(bbsVO.getB_seq());
			}
			ret = fileDao.insertFiles(fileNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	

	@Override
	public void create_bbs_table() {
		bbsDao.create_bbs_table();
	}

	@Override
	public List<BBsVO> selectAll() {
		// TODO Auto-generated method stub
		return bbsDao.selectAll();
	}

	@Override
	public BBsVO findById(Long id) {
		return bbsDao.findById(id);
	}

	@Override
	public int insert(BBsVO vo) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm:SS");
		vo.setB_date(dateForm.format(date));
		vo.setB_time(timeForm.format(date));
		return bbsDao.insert(vo);
	}

	@Override
	public int update(BBsVO vo) {
		// TODO Auto-generated method stub
		return bbsDao.update(vo);
	}
				

	@Override
	public int delete(Long id) {
		
		bbsDao.findById(id);
		bbsDao.delete(id);
		
		return 0;
	}

	@Override
	public List<BBsVO> findByUsername(String username) {
		// TODO Auto-generated method stub
		return bbsDao.findByUsername(username);
	}

}
