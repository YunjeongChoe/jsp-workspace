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
<!-- 
	쿠키 :  브라우저에 저장되는 데이터
	      , 로그인(x), id기억하기, 일주일간 공지사항 안보기 등등
	브라우저는 요청할 때 마다 무조건 가지고있는 쿠키를 전부 request에 실어서 같이 보낸다
 -->

<% 
	Cookie cookie = new Cookie("cookie", "value123");
	response.addCookie(cookie);
	
	Cookie[] cookies = request.getCookies();
	for(Cookie cook : cookies){
		out.print(cook.getName()+" : " + cook.getValue()+ "<br>");
	}
%>



</body>
</html>