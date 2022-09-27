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
	<form action="todoUpdate.wow" method="post">
			<input type="hidden" name="tdNo" value="${view.tdNo}">
			<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>글 제목</th>
					<td><input class="form-control input-sm" type="text" name="tdTitle" value="${view.tdTitle}"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${view.tdWriter }</td>
				</tr>
				<tr>
					<th>글 내용</th>
					<td><textarea class="form-control input-sm" name="tdContent" rows="3">${view.tdContent }</textarea></td>
				</tr>
			</tbody>
		</table>
			<button type="submit">저장</button> <a href="todoList.wow">취소</a>
	</form>
	</div>
</body>
</html>