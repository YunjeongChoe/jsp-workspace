<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("utf-8"); //하는일은 decoding인데 이름은 encoding.. %>
<!-- 톰캣은 get방식일 때 자동으로 decoding해줌 -->
<!-- request.setCharacterEncoding은 파라미터 디코딩
	  contentType의 charSet은 response에 보낼 때 인코딩	
 -->
 <!-- 습관적으로 request.setCharetEncoding(utf-8),
 	 charSet ; UTH-8
 -->
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String kor=request.getParameter("한글");
	String eng=request.getParameter("eng");

%>
한글 : <%=kor %> <br>
eng : <%=eng %>
</body>
</html>