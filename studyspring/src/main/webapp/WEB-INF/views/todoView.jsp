<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

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
	
	<div>
		<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>글 제목</th>
					<td>${view.tdTitle }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${view.tdWriter }</td>
				</tr>
				<tr>
					<th>글 내용</th>
					<td>${view.tdContent }</td>
				</tr>
			</tbody>
		</table>
		<a href="todoModify.wow?tdNo=${view.tdNo }">수정</a> <a href="todoDelete.wow">삭제</a>
	</div>
</body>
</html>