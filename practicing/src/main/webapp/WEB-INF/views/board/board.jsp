<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%request.setCharacterEncoding("utf-8"); %>
</head>
<body>
	<div>저녁 뭐 먹나요? </div>
	<table>
		<thead>
			<tr>
				<th>해킹완료s</th>
				<th>MBC라디오~</th>
				<th>작성자</th>
				<p>윤정초lyeedddddddddddddddddddddddddddddddddddddd<p>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList }" var="board">
				<tr class="text-center">
					<td>${board.boNo }</td>
					<td>${board.boTitle }</td>
					<td>${board.boWriter }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
	</script>
</body>
</html>