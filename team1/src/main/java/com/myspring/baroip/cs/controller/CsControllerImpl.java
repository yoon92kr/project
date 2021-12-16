package com.myspring.baroip.cs.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.cs.service.CsService;
import com.myspring.baroip.cs.vo.CsVO;
import com.myspring.baroip.user.vo.UserVO;



@Controller("csController")
@RequestMapping(value="/cs")
public class CsControllerImpl implements CsController {
	@Autowired
	CsService csService;
	@Autowired
	CsVO csVO;
	
	
	// 1:1 문의 상세보기
		@RequestMapping(value= "/*" ,method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView cs(HttpServletRequest request, HttpServletResponse response) throws Exception{
			// HttpSession session;
			ModelAndView mav = new ModelAndView();
			String viewName = (String)request.getAttribute("viewName");
			mav.setViewName(viewName);
			return mav;
		}
	
//	자주 묻는 질문
	@Override
	@RequestMapping(value= "/cs_01.do" , method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView cs_01(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		// HttpSession session;
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		List<CsVO> QAList = csService.QAList();
//		System.out.println(QAList.get(0).getNotice_title());
		mav.addObject("QAList", QAList);
		mav.addObject("QAListSize", QAList.size());
//		System.out.println(QAList.size());
		mav.setViewName(viewName);
		return mav;
	}
	
	// 1:1문의 리스트
	@Override
	@RequestMapping(value= "/cs_02.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView cs_02(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		List<CsVO> questList = csService.questList();

		for (int i = 0 ; i < questList.size() ; i++) {
//			System.out.println(questList.get(i).getNotice_private());
			if (questList.get(i).getNotice_private().equals("1")) {
				questList.get(i).setNotice_private("공개");
			}
			else {
				questList.get(i).setNotice_private("비공개");
			}
		}
		
//		System.out.println(questList.get(0).getNotice_title());
		mav.addObject("questList", questList);
//		System.out.println(QAList.size());
		mav.setViewName(viewName);
		return mav;
	}
	
	// 1:1 문의 작성
		@RequestMapping(value= "/addQuest.do" ,method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView addQuest(@ModelAttribute("csVO") CsVO csVO, 
				HttpServletRequest request, 
				HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			HttpSession session=request.getSession();
			UserVO user = (UserVO) session.getAttribute("userInfo");
			String user_id = user.getUser_id();
			csVO.setUser_id(user_id);
			csService.addNewQuest(csVO);
			System.out.println(user_id);
			System.out.println(csVO.getNotice_title());
			mav.addObject("pageInfo", csVO);
			mav.setViewName("viewName");
			try {
				mav.setViewName("/cs/cs_02_02");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		}
	
	
	// 1:1 문의 상세보기
	@Override
	@RequestMapping(value= "/quest_datail.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView quest_datail(@RequestParam("notice_id") String notice_id, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
//		 HttpSession session;
		csVO = csService.questDetail(notice_id);
		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
		System.out.println(csVO.getNotice_id());
		mav.addObject("pageInfo", csVO);
		mav.setViewName("/cs/cs_02_02");
		return mav;
	}

	
}
