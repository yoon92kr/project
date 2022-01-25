package com.myspring.baroip.user.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.user.dao.UserDAO;
import com.myspring.baroip.user.vo.UserVO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;



@Service("userService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO login(Map  loginMap) throws Exception {
		return userDAO.login(loginMap);
	}
	
	@Override
	public String addUser(UserVO userVO) throws Exception {
		/* userDAO.insertNewUser(userVO); */
		return userDAO.insertNewUser(userVO);
	}
	
	@Override
	public String userIdOverlap(String id) throws Exception{
		return userDAO.selectIdOverlap(id);
	}
	
//	비회원 주문시 아이디 생성 및 아이디 조회
	@Override
	public String guestJoin() throws Exception {
		return userDAO.insertGuestId();
	}
	
//	naver로그인 시 아이디 생성
	@Override
	public void naverLogin(UserVO userVO) throws Exception {
		userDAO.addNaverUser(userVO);
	}
	
//	핸드폰 인증
	@Override
	public void userPhoneCheck(String mobile, int randomNumber) {
		String api_key = "NCSSVQWN3YKS1DCX";
		String api_secret = "WTMKELCNIM41T5XSQYCAWFLV4ZLIA9RA";
		Message coolsms = new Message(api_key, api_secret);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", mobile);
		params.put("from", "01075422294");
		params.put("type", "SMS");
		params.put("text", "바로입 인증번호는 " + "[" + randomNumber + "]" + " 입니다.");
		params.put("app_version", "test app 1.2");
		
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch(CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	
//	아이디 찾기
	@Override
	public String userIdFind(UserVO userVO) throws Exception {
		return userDAO.userIdFind(userVO);
	}
	
//	비밀번호 찾기 전 회원 정보 일치 확인
	@Override
	public String inputUserCheck(UserVO userVO) throws Exception {
		System.out.println("service : " + userDAO.userIdFind(userVO));
		return userDAO.inputUserCheck(userVO);
	}

}
