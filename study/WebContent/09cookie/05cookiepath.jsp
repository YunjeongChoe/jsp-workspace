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
	//설정된 경로 포함, 하위경로에서 쿠키 관찰
	//따로 경로 설정 안하면 현재 경로가 그 쿠키의 경로
	Cookie path1 = new Cookie("path1", "path1");
	Cookie path2 = new Cookie("path2", "path2");
	Cookie basic = new Cookie("basic", "basic");
	Cookie absolute = new Cookie("absolute", "absolute");

	path1.setPath(request.getContextPath()+"/09cookie/path1");
	path2.setPath(request.getContextPath()+"/09cookie/path2");
	absolute.setPath(request.getContextPath());

	response.addCookie(path1);
	response.addCookie(path2);
	response.addCookie(basic);
	response.addCookie(absolute);


%>

</body>
</html>