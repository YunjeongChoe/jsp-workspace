<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- get방식 요청방법 : url 직접입력. <a href>, <form action="" method="get"> 
	 post방식 요청방법 : <form action="" method="post">
-->

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
%>
파라미터 id값 : <%=id %> <br>
파라미터 pw값 : <%=pw %> <br>
<hr>
<a href="04aGet.jsp?p1=하이">a태그로 가는건 get방식</a>





</body>
</html>