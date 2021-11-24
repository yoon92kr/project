package com.myspring.baroip.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value= "/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		return mav;
	}
	
	@RequestMapping(value= "/myPage_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView user_mypage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}

	// 로그인 페이지
	@RequestMapping(value= "/login_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	// 아이디 비밀번호 찾기
	@RequestMapping(value= "/login_02.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login_02(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	// 아이디 찾기 결과
	@RequestMapping(value= "/login_03.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login_03(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	// 비밀번호 찾기
	@RequestMapping(value= "/login_04.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login_04(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	
	// 비밀번호 찾기 완료
	@RequestMapping(value= "/login_05.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login_05(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	// 공지사항 페이지
	@RequestMapping(value= "/notice_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView notice_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	// 공지사항 상세페이지
		@RequestMapping(value= "/notice_02.do" ,method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView notice_02(HttpServletRequest request, HttpServletResponse response) throws Exception{
			// HttpSession session;
			ModelAndView mav = new ModelAndView();
			String viewName = (String)request.getAttribute("viewName");
			mav.setViewName(viewName);
			return mav;
		}
	
	// 장바구니 페이지
	@RequestMapping(value= "/cart.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView cart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
}
