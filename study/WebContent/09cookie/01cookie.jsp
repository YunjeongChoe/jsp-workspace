<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("utf-8");%>
<%@ include file="/WEB-INF/inc/header.jsp" %>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>

<%
	//현재 브라우저가 가지고 있는 쿠키들을 전부 화면에 출력하려고 할 때
	Cookie[] cookies = request.getCookies();
	//배열을 return하는 메소드는 조심해야 될 것
	//null을 return하는지, 길이가 0인 배열을 return하는지
	if(cookies != null){
		for(Cookie cookie : cookies){
			out.print(cookie.getName() + " : " + cookie.getValue());
			out.print("<br>");
		}
	}
	out.print("<hr>");
	//새로운 쿠키를 만들어서 브라우저한테 주려고 할 때 response에 실어보냄
	
	Cookie cookie = new Cookie("han", "sdfasdf");
	response.addCookie(cookie);
	
	
%>










</body>
</html>