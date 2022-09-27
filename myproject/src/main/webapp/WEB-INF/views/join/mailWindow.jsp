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
 <div class="container">
		<div class="row col-md-8 col-md-offset-2">
			<div class="page-header">
				<h3>이메일인증</h3>
			</div>
			<table class="table">	
				<tr>
					<td>
					<input type="text" name="authKey" class="form-control input-sm" placeholder="인증번호를 입력하세요">
						<button onclick="window.close();" type="button" id="authKeyCompare" class="btn btn-primary">확인</button>
					</td>
				</tr>
			</table>
		</div>
	</div> 
</body>
</html>