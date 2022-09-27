<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>

<a href="freeForm.wow">글쓰기</a> <br>
<table border="1">
	<thead>
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>도수</td>
			<td>도수</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${alcoholList }" var="alcohol">
			<tr>
				<td>${alcohol.boardNo  }</td>
				<td>${alcohol.boardNo }</td>
				<td>${alcohol.boardTitle }</td>
				<td>${alcohol.boardAuthor }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>