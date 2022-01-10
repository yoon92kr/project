// 2022.01.10 윤상현

package com.myspring.baroip.adminNotice.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.myspring.baroip.adminNotice.service.AdminNoticeService;
import com.myspring.baroip.notice.vo.NoticeVO;

@Controller("adminNoticeController")
@RequestMapping(value="/admin/notice")
public class AdminNoticeControllerImpl implements AdminNoticeController {
	@Autowired
	AdminNoticeService adminNoticeService;
	
	// 공지관리 페이지 전체 mapping
	@Override
	@RequestMapping(value = "/*", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView adminNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
//	관리자페이지 공지관리 컨트롤러
	@RequestMapping(value= "/notice_list.do", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView adminNoticeList(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		// get 요청이 없을경우, 기존의 session을 제거
		if (info.isEmpty()) {
			session.removeAttribute("search_option");
			session.removeAttribute("search_value");
		}
		
		info.put("notice_category", "notice");
		Map<String, Map<String, Object>> noticeList = getFullList(info, request);
		
		String pageNo = info.get("pageNo");
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		
		if (pageNo != null && pageNo != "") {
			int lastNo = (noticeList.size()+6)/7;
			
			if (Integer.parseInt(pageNo) > lastNo) {
				
				mav.addObject("pageNo", 1);
				mav.addObject("message", "잘못된 요청입니다.");
			}
			else {
				mav.addObject("pageNo", pageNo);	
			}
			
		} else {
			mav.addObject("pageNo", 1);
		}
		mav.addObject("noticeList", noticeList);
		mav.setViewName(viewName);

		return mav;
	}
	
	// 공지 등록 컨트롤러
	@Override
	@RequestMapping(value = "/add_notice.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addNotice(@ModelAttribute("noticeVO") NoticeVO noticeVO, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String message = "[" + "]의 임시등록이 완료되었습니다.";
		
		HttpSession session = request.getSession();
		
		session.setAttribute("message", message);
		mav.setViewName(viewName);
		
		return mav;
	}
	
	// 공지 삭제 컨트롤러
	@Override
	@RequestMapping(value = "/delete_notice.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView deleteNotice(@RequestParam("notice_id") String notice_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();

		// System.out.printf("baroip : [%s]상품이 정삭적으로 삭제되었습니다.%n", product_id);
		mav.setViewName("redirect:/admin/product/extra_list.do");

		return mav;
	}

	// 공지 수정 양식 컨트롤러
	@Override
	@RequestMapping(value = "/update_notice_form.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateNoticeForm(@RequestParam("notice_id") String notice_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");

		// Map<String, Map<String, Object>> productInfo = productService.productDetail(product_id);

		// mav.addObject("productInfo", productInfo);
		mav.setViewName(viewName);
		return mav;

	}

	// 공지 수정 컨트롤러
	@Override
	@RequestMapping(value = "/update_notice.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateNotice(@ModelAttribute("noticeVO") NoticeVO noticeVO, HttpServletRequest Request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		// String lastViewName = (String)multipartRequest.getParameter("last_view_name");
		// adminProductService.updateProduct(productVO);
		// String message = "관리자 " + productVO.getUser_id() + " 님이 [" + productVO.getProduct_main_title() + "]의 수정을 완료되었습니다.";

		// HttpSession session = multipartRequest.getSession();
		// session.setAttribute("message", message);
		// imageController.updateImage(multipartRequest, productVO.getProduct_id());
		// mav.setViewName("redirect:"+lastViewName+".do");
		// System.out.println("baroip : " + message);

		return mav;
	}
	
	// 상품 조회 필터 사용시, 세션에 있는 상품정보를 확인 후 서비스로 처리하는 메소드
		public Map<String, Map<String, Object>> getFullList(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception {
			
			HttpSession session = request.getSession();
			
			// Map options에는 조회하고자 하는 조건명 notice_category / search_option / search_value / search_state 이 있으며, notice_category은 반드시 포함되어야한다.
			// * notice_category(notice_category 설정) = value [comment / refund / product_quest / user_quest / notice / QA]
			// search_option(검색 조건) = value [productCreDate / productTitle / productStates / productAmount]
			// search_value(검색 값) = value [yyyy-mm-dd,yyyy-mm-dd / product_main_title / 0 or 1 or all) / int]
			// search_state(답변 상태) = value [all / y / n]
			
			Map<String, String> options = new HashMap<String, String>();
			String paramOption = info.get("search_option");
			String paramValue = info.get("search_value");
			String paramState = info.get("search_state");
			
			String sessionOption = (String) session.getAttribute("search_option");
			String sessionValue = (String) session.getAttribute("search_value");
			String sessionState = (String) session.getAttribute("search_state");

			// search_option 검색조건이 session 혹은 param에 존재할 경우 처리
			if (paramOption != null || sessionOption != null) {
				
				// param에 조건이 있을경우
				if(paramOption != null && sessionOption == null) {
					options.put("search_option", paramOption);
					session.setAttribute("search_option", paramOption);
				}
				// session에 조건이 있을경우
				else if(paramOption == null && sessionOption != null) {
					options.put("search_option", sessionOption);
				}
				// param과 session 모두에 조건이 있을경우
				else {
					// param과 session의 조건이 동일할 경우, 기존 session의 조건 전달
					if(paramOption.equals(sessionOption)) {
						options.put("search_option", sessionOption);
					}
					// param과 session의 조건이 다를 경우, session에 set후, param 조건 전달
					else {
						options.put("search_option", paramOption);
						session.setAttribute("search_option", paramOption);
					}
				} 
			
			}
			// search_value 검색조건이 session 혹은 param에 존재할 경우 처리			
			if (paramValue != null || sessionValue != null) {
				
				// param에 조건이 있을경우	
				if(paramValue != null && sessionValue == null) {
					options.put("search_value", paramValue);
					session.setAttribute("search_value", paramValue);
				}
				// session에 조건이 있을경우
				else if(paramValue == null && sessionValue != null) {
					options.put("search_value", sessionValue);
				}
				// param과 session 모두에 조건이 있을경우
				else {
					// param과 session의 조건이 동일할 경우, 기존 session의 조건 전달
					if(paramValue.equals(sessionValue)) {
						options.put("search_value", sessionValue);
					}
					// param과 session의 조건이 다를 경우, session에 set후, param 조건 전달
					else {
						options.put("search_value", paramValue);
						session.setAttribute("search_value", paramValue);
					}
				} 
			
			} 
			// search_state 검색조건이 session 혹은 param에 존재할 경우 처리			
			if (paramState != null || sessionState != null) {
				
				// param에 조건이 있을경우
				if(paramState != null && sessionState == null) {
					options.put("search_state", paramState);
					session.setAttribute("search_state", paramState);
				}
				// session에 조건이 있을경우
				else if(paramState == null && sessionState != null) {
					options.put("search_state", sessionState);
				}
				// param과 session 모두에 조건이 있을경우
				else {
					// param과 session의 조건이 동일할 경우, 기존 session의 조건 전달
					if(paramState.equals(sessionState)) {
						options.put("search_state", sessionState);
					}
					// param과 session의 조건이 다를 경우, session에 set후, param 조건 전달
					else {
						options.put("search_state", paramState);
						session.setAttribute("search_state", paramState);
					}
				} 
			
			} 
			options.put("notice_category", info.get("notice_category"));
			Map<String, Map<String, Object>> fullList = adminNoticeService.noticeListToOption(options);
			
			return fullList;
		}
}
