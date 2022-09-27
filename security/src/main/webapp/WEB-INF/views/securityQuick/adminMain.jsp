<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("utf-8");%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<sec:authorize access="hasAuthority('ROLE_ADMIN')" >
	이름 : <sec:authentication property="name"/> <br>
	권한 : <sec:authentication property="authorities"/>
</sec:authorize>

<form action="<%=request.getContextPath() %>/logout" method="post">
	<sec:csrfInput/>
	<button type="submit">로그아웃</button>
</form>


</body>
</html>