<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--buffer는 out이랑 관련있음  out.print("<html>")
out은 response랑 관련이 있음.
(브라우저에게 전달할) 데이터를 임시저장 / 데이터 = html태그인 문자열
 -->
<!--  8kb  8 * 1024 byte
       한글 2,3byte -->
 	   <%-- <html>태그부터 </html>까지 8000byte가 보통은 되지 않음 넘어도 autoFlush() 해주니까 괜찮다.--%>
<%
	long startTime = System.nanoTime();
	for(int i = 0; i < 10000; i++){
		out.print("술이 좋아");
//		out.flush();     //버퍼에 있는거를 브라우저에 전달.
	out.clear();        //버퍼에 있는거를 비움
	}
	long endTime = System.nanoTime();
	out.print(endTime - startTime);
%>

</body>
</html>