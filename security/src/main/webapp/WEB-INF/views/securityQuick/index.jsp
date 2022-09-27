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
<!-- 권한 중에서 특정 권한이 아닌 로그인 여부를 확인하는 -->
<sec:authorize access="isAuthenticated()" >
	이름 : <sec:authentication property="name"/> <br>
	권한 : <sec:authentication property="authorities"/>
</sec:authorize>

<!-- 로그아웃은? form태그 post방식으로 + 현재 로그인정보를 꼭 파라미터로 넘길 수 있도록 -->
<form action="<%=request.getContextPath() %>/logout" method="post">
	<sec:csrfInput/>
	<button type="submit" >로그아웃</button>
</form>






</body>
</html>