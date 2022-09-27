<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	String idol = request.getParameter("idol");
	String viewPage = "";
	List<String> memberList = new ArrayList<>();
	if("aoa".equals(idol)){
		viewPage = "04aoa.jsp";
		memberList.add("설현");
		memberList.add("나머지");
	}else if("bts".equals(idol)){
		viewPage="04bts.jsp";
		memberList.add("뷔");
		memberList.add("정국");
		memberList.add("랩몬스터");
		memberList.add("나머지");
	}
	request.setAttribute("memberList", memberList);
%>
	이 곳에는 아무거나 써도 상관없음. 
	중요한건 aoa랑 bts화면 보여줄 때 화면양식이 다르면 aoa.jsp랑 bts.jsp를 따로 만들 수 밖에 없음.
	(화면양식이 같으면 굳이 jsp를 2개 안만들고 1개만 만들어도 됨)
	
	form.jsp에서 뭘 선택했느냐에 따라 aoa.jsp 또는 bts.jsp로 이동하도록 해야함	
	forward돼도 역시 같은 request를 공유<!-- 화면에 안나오는 이유: buffer에 담겼다가 지워짐 -->
	
	<jsp:forward page="<%=viewPage %>"></jsp:forward>


</body>
</html>