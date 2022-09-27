<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

	글쓰기 페이지
	<div class="container">
		<form:form action="todoRegist.wow" method="post" modelAttribute="todo">
			<table class="table table-striped table-bordered">
				<tr>
					<th>제목</th>
					<td><form:input path="tdTitle"
							cssClass="form-control input-sm" /> 
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><form:input path="tdWriter"
							cssClass="form-control input-sm" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><form:textarea path="tdContent" cssClass="form-control"
							rows="10" /> </td>
				</tr>
				<tr>
					<td>
						<div class="pull-right">
							<button type="submit" class="btn btn-sm btn-primary">
								<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
								&nbsp;&nbsp;저장
							</button>
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>