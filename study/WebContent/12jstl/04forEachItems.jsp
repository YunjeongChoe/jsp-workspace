<%@page import="com.study.login.vo.UserVO"%>
<%@page import="java.util.Map"%>
<%@page import="com.study.common.util.UserList"%>
<%@page import="com.study.common.util.ProductList"%>
<%@page import="com.study.common.vo.ProdVO"%>
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
<%@ include file="/WEB-INF/inc/top.jsp" %>

<%
	String[] strs={"임동성", "가나혜", "최윤정", "한창희"};
	request.setAttribute("strs", strs);
%>
<table border="1" class="table table-striped">
	<tbody>
		<c:forEach items="${strs }" var="str" varStatus="status">
		<tr>
			<td>${status.count}.  ${str }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<%
	List<ProdVO> prodList = ProductList.getProductList();
	request.setAttribute("prodList", prodList);
%>

<table border="1">
	<thead>
	<tr>
		<td>제품아이디</td>
		<td>제품이름</td>
		<td>가격</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${prodList }" var="prod">
			<tr>
			<td>${prod.prodId }</td>
			<td>${prod.prodName }</td>
			<td>${prod.prodPrice }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%
	Map<String, UserVO> userMap=new UserList().getUsersMap();
	//{"malja", UserVO(malja, 말자, 1004, Member)}
	request.setAttribute("userMap", userMap);
%>
<c:forEach items="${userMap }" var="entry">
	<h3>${entry.key }</h3>
	<h3>${entry.value.userName }</h3>
	<hr>
</c:forEach>



</body>
</html>