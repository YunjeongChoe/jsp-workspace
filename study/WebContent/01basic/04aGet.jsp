<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
04aGet.jsp는 a태그를 통해서 온 겁니다. <br>
<%
	String p1 = request.getParameter("p1");
%>
파라미터 p1= <%=p1 %>

</body>
</html>