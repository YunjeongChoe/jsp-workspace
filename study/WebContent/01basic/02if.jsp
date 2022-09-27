<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	//http://localhost:8080/study/01basic/02if.jsp
	//                                            ?ch=HanChangHee
	String ch = request.getParameter("ch");
	/* if(ch == null){
		out.print("파라미터가 없어요");
	}else{
		out.print("ch: " + ch);
	} */
%>

<%
	if(ch == null){
%>
	파라미터가 없어요!
<!-- 	<table border="1"><tr><td>adsfasdf</td></tr></table>
	<a href="aaaaa">dfasdfdf</a>-->
<%
	}else{
%>
	ch : <%out.print(ch); %>
	ch : <%=ch %>
<!-- 	<form action="hddf"><input type="text" value="" name="fff"></form> -->
<%
	}
%>




</body>
</html>