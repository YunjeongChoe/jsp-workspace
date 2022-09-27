<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
EL은 기본적으로 출력이 되고, 언어라서 변수선언이 된다.
변수만 선언하고 화면에 출력 안되게 할 때 ;앞은 출력 안함
기본적으로 EL은 빈 값이 있으면 안된 <%-- ${}은 에러남 --%>
<br>
${a="안녕" ; ""}
<br>
${a }

</body>
</html>