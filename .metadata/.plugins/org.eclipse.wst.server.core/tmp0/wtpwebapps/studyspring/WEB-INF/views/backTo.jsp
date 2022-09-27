<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<% request.setCharacterEncoding("utf-8");%>
<%@ include file="/WEB-INF/inc/header.jsp" %>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>

<div>
	<button onclick="location.href='todoList.wow'" >글 목록</button>
<a href="todoList.wow" class="btn btn-default btn-sm">
<span class="glyphicon glyphicon-list" aria-hidden="true"></span>목록</a>
</div>

</body>
</html>