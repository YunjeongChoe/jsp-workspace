
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%request.setCharacterEncoding("utf-8"); %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
	//String hobby = request.getParameter("hobby");
	String[] hobbies = request.getParameterValues("hobby");
	String[] names = request.getParameterValues("name");
	//names[0]=request.getParameter("name");
%>

<h1>모든 파라미터 한번에 Map으로 보기 Set for반복 </h1>
<%
	Map<String, String[]> paramMap = request.getParameterMap();
	/* [
	 "name" : {"입력한 값"}, 
	  "age" : {"10"}, 
	  "hobby" : {"soccer","basketball","piano","coding"}
	  ] */
	Set<String> keySet = paramMap.keySet();
	//{"name", "age", "hobby"}
	for(String key : keySet){
		out.print(key+" : " + paramMap.get(key) + "<br>");
		String[] values = paramMap.get(key);
		for(String value : values){
			out.print(value + ",");
		}
		out.print("<br>");
	}
%>	
<h1>파라미터 한번에 SET iterator</h1>
<%
	Iterator<String> iterator = keySet.iterator();
	while(iterator.hasNext()){
		String key = iterator.next();
		String[] values = paramMap.get(key);
		for(String value : values){
			out.print(value + ",");
		}
		out.print("<br>");
	}
%>
<%
	//Entry는 Map내부 인터페이스
	Set<Map.Entry<String, String[]> > entrySet = paramMap.entrySet();
	for(Map.Entry<String,String[]> entry : entrySet){
		String key = entry.getKey(); //name, age, work
		String[] values = entry.getValue(); // {}, {}, {, , , ,}
	}
//Entry : key, value
%>


</body>
</html>