<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="01errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ArrayindexOutOfBounds
	 Cast
	 NullPointer 등등
 -->
<%
	//int[] arr = new int[3];
	//arr[10] = 10;
	//application.setAttribute("request", request);
	//HttpServletResponse resp = (HttpServletResponse)application.getAttribute("request");
	
	//NullPointerException, null.method(),  null+3
	//파란 에러화면은 코드노출, 사이트 신뢰성X
	//에러가 전혀 안나게 만들기는 힘듦, 에러가 났을 때 파란화면 대신, 다른화면을 보여준다.
	String a = null;
	a.charAt(0);
%>







</body>
</html>