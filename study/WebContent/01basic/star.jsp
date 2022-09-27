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
		String star = "";
	for(int i = 1; i <= 5; i++){
		star += "*";
		out.print(star);
		out.print("<br>");
	}

%>

 <hr><hr><hr>

<%	String star1 = "*";
	String blank = "&nbsp;&nbsp;";
for(int j = 0; j < 5; j++){
	out.print(blank);
	blank += "&nbsp;&nbsp;";
	for(int i = 0; i < 5-j; i++){
		out.print(star1);
	}
	out.print("<br>");
}
%>

<hr><hr><hr>

<%
	star1 = "*";
	for(int i = 0; i < 5; i++){
	String blank1 = "";
		for(int j=0; j < 5 - i; j++){
		blank1 += "&nbsp;&nbsp;";
			out.print(blank1 + star1);
			out.print(star1);
		}
		star1 += "**";
		out.print("<br>");
	}
%>




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