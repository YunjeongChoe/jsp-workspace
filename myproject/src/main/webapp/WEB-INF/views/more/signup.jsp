<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/inc/header.jsp"%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div id="form">
		<form action="userRegist.wow" method="post">
			아이디: <input name="memId" type="text"> 
			모바일: <input name="memPh" type="text">
			<button type="button" id="submit">확인</button>

		</form>

	</div>




</body>
</html>