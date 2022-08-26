package com.callor.food.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.callor.food.model.BBsVO;
import com.callor.food.service.BBsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/food")
public class FoodController {

	@Autowired
	private BBsService bbsService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String home(Principal principal,Model model) {

		// Spring Security Project 에서 로그인한 사용자의 
		// username 을 get 하기
		String username = principal.getName();
		
		// 만약 혹시, 로그인된 사용자 정보를 알수 없으면
		// 로그인 화면으로 redirect
		if(username == null) {
			return "redirect:/user/login?error=LOGIN_NEED";
		}
		
		// 사용자의 username 이 정상이면
		// 데이터 SELECT 하기
		List<BBsVO> bbsList = bbsService.findByUsername(username);
		
		log.debug("데이터 {}",bbsList);
		model.addAttribute("FOODS", bbsList);
		model.addAttribute("LAYOUT","FOOD_LIST");
		
		return "home";
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public  String insert(@ModelAttribute("bbsVO") BBsVO bbsVO,
			 Model model) {
		model.addAttribute("LAYOUT","FOOD_INSERT");
		return "home";
	}

	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public  String insert(@ModelAttribute("bbsVO") BBsVO bbsVO,
			@RequestParam("mFile") MultipartFile mFile, Model model) {
		String imageFile = bbsService.insertBbsAndFile(bbsVO, mFile);
		model.addAttribute("IMAGE",imageFile);
		model.addAttribute("LAYOUT","FOOD_INSERT");
		return "redirect:/food";
	}

	
	
	@RequestMapping(value= {"/",""},method=RequestMethod.POST)
	public  String insert(Principal principal, BBsVO bbsVO) {
		
		String username = principal.getName();
		if(username == null) {
			return "redirect:/user/login?error=LOGIN_NEED";
		}
		bbsVO.setB_username(username);
		bbsService.insert(bbsVO);
		return "food/insert";
	}
	
	
	@RequestMapping(value="/update/{seq}",method=RequestMethod.GET)
	public String update(@PathVariable(name = "seq")  String seq,String b_seq, Model model) {
		
		Long l_seq = 0L;
		try {
			l_seq = Long.valueOf(b_seq);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		BBsVO todoVO = bbsService.findById(l_seq);
		model.addAttribute("FOOD",todoVO);
		model.addAttribute("LAYOUT","FOOD_LIST");
		return "home";
		
	}
	
	
	@RequestMapping(value="/update/{seq}",method=RequestMethod.POST)
	public String update(@PathVariable(name = "seq")  String seq,BBsVO bbsVO, Model model) {
		
		log.debug("수신된 데이터 {}", bbsVO);
		bbsService.update(bbsVO);
		return "redirect:/food";
	}
	@RequestMapping(value="/detail/{seq}",method=RequestMethod.GET)
	public String detail(@PathVariable(name = "seq")  String seq, Model model) {
		
		try {
			long b_seq = Long.valueOf(seq);
			BBsVO bbsVO = bbsService.findById(b_seq);
			log.debug("bbsVO {}", bbsVO);
//			bbsVO.setImages(fileDao.fineByBBsSeq(b_seq));
			model.addAttribute("BBS",bbsVO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("LAYOUT","FOOD_DETAIL");
		return "home";
	}
	@ModelAttribute("bbsVO")
	private BBsVO bbsVO() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		BBsVO bbsVO = BBsVO.builder()
						.b_seq(0)
						.b_date(dayFormat.format(date))
						.b_time(timeFormat.format(date))
						.b_writer("lee98")
						.build();
		log.debug(bbsVO.toString());
		return bbsVO;
	}
}
