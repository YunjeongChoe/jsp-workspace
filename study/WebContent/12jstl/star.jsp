<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% String star = "*";%>
<c:forEach begin="1" end="5" var="i">
	
<% out.print(star); %>

	<br>
	<%star += "*"; %>
</c:forEach>

<hr><hr><hr>

<%star = ""; 
String blank = ""; %>
<c:forEach begin="1" end="5" var="i">
	<%=blank%>
	<c:forEach begin="1" end="${6-i }" var="j">
	<%star = "*";%>
	<%=star %>
	</c:forEach>
	<%blank += "&nbsp;&nbsp;";%>
	<br>
</c:forEach>

<hr><hr><hr>

<%star = "*"; 
  blank = "&nbsp;&nbsp;"; %>
<c:forEach begin="1" end="5" var="i">
	<c:forEach begin="1" end="${6-i }" var="j">
		<%=blank %>
	</c:forEach>
		<%=star %>
		<%star += "**";%>
	<br>
</c:forEach>

<hr><hr><hr>







*<br>
**<br>
***<br>
****<br>
*****<br>

<hr><hr><hr>

&nbsp;&nbsp;*****<br>
&nbsp;&nbsp;&nbsp;&nbsp;****<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;***<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*<br>

<hr><hr><hr>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;***<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*****<br>
&nbsp;&nbsp;&nbsp;&nbsp;*******<br>
&nbsp;&nbsp;*********<br>







</body>
</html>