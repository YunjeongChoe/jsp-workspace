<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("utf-8"); %>>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% //name, age, work  ;
	String name=request.getParameter("name");
	String age=request.getParameter("age");
	String work=request.getParameter("work");
%>
<h1>기본정보</h1>
IP : <%=request.getRemoteAddr() %> <br>
웹 경로 : <%=request.getContextPath() %> <br>
URL : <%=request.getRequestURL() %> <br>
URI : <%=request.getRequestURI() %> <br>

<hr>
<h1>request 헤더들</h1>
host : <%=request.getHeader("host") %> <br>
content-length : <%=request.getHeader("content-length") %> <br>
referer : <%=request.getHeader("referer") %> <br>
user-agent : <%=request.getHeader("user-agent") %> <br>

<hr>
<h1>모든 헤더 한번에 보기</h1>
<%
//메소드 : 매개변수, return type, 이름(+ 설명)
	Enumeration <String> headerNames = request.getHeaderNames();
	//{host, referer, user-agent, ......}
	while(headerNames.hasMoreElements()){
		String headerName = headerNames.nextElement();
		String headerValue = request.getHeader(headerName);
		out.print(headerName + " : " + headerValue+"<br>");
	}
%>

<hr>
<h1>파라미터 보기</h1>
name, age, work
이름 : <%=request.getParameter("name")%> <br>
나이 : <%=request.getParameter("age")%> <br>
직장 : <%=request.getParameter("work")%> <br>

<hr>
<h1>모든 파라미터 보기</h1>
<%
	Enumeration<String> paramNames=request.getParameterNames();
	//반복 {name, agem work}
	while(paramNames.hasMoreElements()){
		String paramName = paramNames.nextElement();
		String paramValue = request.getParameter(paramName);
		out.print(paramName + " : " + paramValue + "<br>");
	}
%>


</body>
</html>