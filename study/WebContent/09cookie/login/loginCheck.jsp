<%@page import="com.study.common.util.CookieUtils"%>
<%@page import="com.study.login.vo.UserVO"%>
<%@page import="com.study.common.util.UserList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title></title>
</head>
<body>

<%
	String userId = request.getParameter("userId");
	String userPass = request.getParameter("userPass");
	
	
	if(userId.isEmpty() || userPass.isEmpty() ){
		response.sendRedirect("login.jsp?msg=Please enter your Id or Password");
	}else{
	
		UserList userList = new UserList();
		UserVO user = userList.getUser(userId);
		
		if(user == null){
			//id틀림
			response.sendRedirect("login.jsp?msg=Wrong Id or Password");
			
		}else{
			//id맞음
			if(user.getUserPass().equals(userPass)){
				Cookie cookie = new Cookie("AUTH", userId);
				response.addCookie(cookie);
				
				String rememberMe = request.getParameter("rememberMe");
				if(rememberMe.equals("Y")){
					Cookie cookie = CookieUtils.createCookie("SAVE_ID", userId, "/", 60*60*24*7);
					response.addCookie(cookie);
					
				}
				CookieUtils cookieUtils = new CookieUtils(request);
				if(cookieUtils.exists("SAVE_ID")){
					Cookie remem = cookieUtils.getCookie("SAVE_ID");
				}
		
				response.sendRedirect("login.jsp?msg=Login Success");
			
			}else{
				response.sendRedirect("login.jsp?msg=Id or Password does not exist");
				
			}
		}
	}
%>



	
	
</body>
</html>