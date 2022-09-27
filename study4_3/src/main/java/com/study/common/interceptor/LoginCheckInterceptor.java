package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.study.login.vo.UserVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	//로그인 여부 검사하기,  로그인 되어있으면(문제가 없으면) true -> 원래 가려던 @RequestMapping메소드로 가면 됨 
	//					  로그인 안 되어있으면(문제 있으면) false -> 못감. redirect : 로그인 페이지로
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("USER_INFO");
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login/login.wow");
			return false;
		}
		
		return true;
		
	}
	
	
	
	
}
