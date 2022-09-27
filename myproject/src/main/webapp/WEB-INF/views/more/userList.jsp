<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<table border="1">
	<tbody>
		<tr>
			<th>이름</th>
			<td>${user.userNm }</td>
		</tr>
		<tr>
			<th>모바일</th>
			<td>${user.userPh }</td>
		</tr>
	</tbody>
</table>
</body>
</html>