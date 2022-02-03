// 2021.12.24 임석희

package com.myspring.baroip.notice.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.image.service.ImageService;
import com.myspring.baroip.notice.service.NoticeService;
import com.myspring.baroip.notice.vo.NoticeVO;
import com.myspring.baroip.product.service.ProductService;


@Controller("noticeController")
@RequestMapping(value="/notice")
public class NoticeControllerImpl implements NoticeController {
	@Autowired
	NoticeService noticeService;
	@Autowired
	NoticeVO noticeVO;
	@Autowired
	private ProductService productService;
	
	@Autowired
	ImageService imageService;
	
	// notice 페이지 전체 mapping
	@Override
	@RequestMapping(value= "/*" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView notice(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
		
	}

	// 2022.01.12 윤상현 수정
	// 공지사항 리스트페이지
	@RequestMapping(value= "/notice_list.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView notice_list(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception{
		// HttpSession session;
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		List<NoticeVO> noticeList = noticeService.noticeList();
		
		String pageNo = info.get("pageNo");
		
		if (pageNo != null && pageNo != "") {
			int lastNo = (noticeList.size()+4)/5;
			
			if (Integer.parseInt(pageNo) > lastNo) {
				mav.addObject("pageNo", 1);
				mav.setViewName("redirect:"+viewName +".do");
			}
			else {
				mav.addObject("pageNo", pageNo);	
				mav.setViewName(viewName);
			}
			
		} else {
			mav.addObject("pageNo", 1);
			mav.setViewName(viewName);
		}
		
		mav.addObject("noticeList", noticeList);
		return mav;
	}
	
	// 공지사항 상세페이지
	@Override
	@RequestMapping(value= "/notice_detail.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView notice_detail(@RequestParam("notice_id") String notice_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		noticeVO = noticeService.noticeDetail(notice_id);
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.addObject("noticeVO", noticeVO);
		mav.setViewName(viewName);
		return mav;
	}
	
//	2022.02.03 한건희 수정
//	상품 후기
	@Override
	@RequestMapping(value= "/productComment.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView productComment(@RequestParam("product_id") String product_id, @RequestParam Map<String, String> info,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNo = info.get("pageNo");
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		
		List<Map<String, Object>> commentList = noticeService.productComment(product_id);
		Map<String, Map<String, Object>> productImg = productService.productDetail(product_id);
		
		if (pageNo != null && pageNo != "") {
			int lastNo = (commentList.size()+3)/4;
			
			if (Integer.parseInt(pageNo) > lastNo) {
				mav.addObject("pageNo", 1);
				mav.setViewName("redirect:"+viewName +".do");
			}
			else {
				mav.addObject("pageNo", pageNo);	
				mav.setViewName(viewName);
			}
			
		} else {
			mav.addObject("pageNo", 1);
			mav.setViewName(viewName);
		}

		mav.addObject("commentList", commentList);
		mav.addObject("product_id", product_id);
		mav.addObject("productInfo", productImg);
		
		mav.setViewName(viewName);
		
		return mav;
		
	}
	
//	2022.02.03 한건희 수정
//	상품 문의
	@Override
	@RequestMapping(value= "/PQAListPage.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView PQAListPage(@RequestParam("product_id") String product_id, @RequestParam Map<String, String> info,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNo = info.get("pageNo");
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		
		Map<String, Object> PQAList = noticeService.productQuestion(product_id);
		Map<String, Map<String, Object>> productImg = productService.productDetail(product_id);
		
		if (pageNo != null && pageNo != "") {
			int lastNo = (PQAList.size()+7)/8;
			
			if (Integer.parseInt(pageNo) > lastNo) {
				mav.addObject("pageNo", 1);
				mav.setViewName("redirect:"+viewName +".do");
			}
			else {
				mav.addObject("pageNo", pageNo);	
				mav.setViewName(viewName);
			}
			
		} else {
			mav.addObject("pageNo", 1);
			mav.setViewName(viewName);
		}
		
		mav.addObject("PQAList", PQAList);
		mav.addObject("product_id", product_id);
		mav.addObject("productInfo", productImg);
		mav.setViewName(viewName);
		return mav;
		
	}
	
	@RequestMapping(value= "/add_PQA_form" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView add_PQA_form(@RequestParam("product_id") String product_id, @RequestParam("product_main_title") String product_main_title, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.addObject("product_id", product_id);
		mav.addObject("product_main_title", product_main_title);
		mav.setViewName(viewName);
		return mav;
		
	}
	
	@RequestMapping(value= "/add_PQA" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView add_PQA(@RequestParam("NoticeVO") NoticeVO noticeVO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		noticeService.addPQA(noticeVO);
		
		mav.setViewName("redirect:/PQAListPage.do");
		return mav;
		
	}
	
	
		
}
