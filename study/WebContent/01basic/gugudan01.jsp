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
//1번
	out.print("<table border='1'>");
	out.print("<tbody>");
	for(int i=1; i <=9; i++){
		out.print("<tr>");
		for(int j=2; j<=9; j++){
			out.print("<td>");
			out.print(j);
			out.print("x");
			out.print(i);
			out.print("=");
			out.print(i*j);
		}
		out.print("</tr>");
		out.print("</td>");
	}
	
%>	


<%
//2번 
	out.print("<table border='1'>");
	out.print("<tbody>");
	for(int i=2; i <=9; i++){
		out.print("<tr>");
		for(int j=1; j<=9; j++){
			out.print("<td>");
			out.print(i);
			out.print("x");
			out.print(j);
			out.print("=");
			out.print(i*j);
		}
		out.print("</tr>");
		out.print("</td>");
	}
	
%>




</body>
</html>