package com.study.login.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.member.service.IMemberService;


@Controller
public class KakaoController {
	
	@Autowired
	IMemberService ms;
	
	
	@RequestMapping(value = "/login/kakaoLogin", method = RequestMethod.GET)
	public String kakao() {
		StringBuffer loginUrl = new StringBuffer();
		loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
		loginUrl.append("b792c927c88597449803f7ffaeb0fafa"); //카카오 앱에 있는 REST KEY
		loginUrl.append("&redirect_uri=");
		loginUrl.append("http://localhost:8080/home/login/kakaoLogin"); //카카오 앱에 등록한 redirect URL
		loginUrl.append("&response_type=code");
		
		return "home";
	}
	
	
//	@RequestMapping(value="/login/kakaoLogin",  method=RequestMethod.GET)
//	public String kakaoLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws Exception {
//		String nm = req.getParameter("kakaoName");
//		String mail = req.getParameter("kakaoMail");
//		
//		return "login/kakaoLogin";
//	}
	
	
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value="/login/kakaoLogin",  method=RequestMethod.GET)
//	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
//		System.out.println("#########" + code);
//		
//		String access_Token = ms.getAccessToken(code);
//		System.out.println("###access_Token#### : " + access_Token);
//		
//		HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
//		System.out.println("###access_Token#### : " + access_Token);
//		System.out.println("###nickname#### : " + userInfo.get("nickname"));
//		System.out.println("###email#### : " + userInfo.get("email"));
//		
//		return "login/kakaoLogin";
//	}
	
	
			
		
	
	
}
