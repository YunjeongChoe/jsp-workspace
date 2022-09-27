package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.study.login.vo.UserVO;

public class ManagerCheckInterceptor extends HandlerInterceptorAdapter{
	
	// /member/* 는 관리자만 접근 가능하다 관리자가 아니면 들어갈 수 없게.
	//관리자인지 아닌지는 UserVO의 userRole을 가지고 판단. UserVO가 session에 담기는 곳은 LoginController - LoginServiceImpl
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("aaaaaaaaaaaaaaaaaaaa");
		HttpSession session=request.getSession();
		UserVO user=(UserVO)session.getAttribute("USER_INFO");
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login/login.wow");
			return false;
		}
		if(!user.getUserRole().equals("MANAGER")) {
			response.sendError(403, "Only manager can enter");
			return false;
		}
		return true;
	}
}
