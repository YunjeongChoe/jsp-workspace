<%@page import="java.util.ArrayList"%>
<%@page import="com.study.common.util.ProductList"%>
<%@page import="com.study.common.vo.ProdVO"%>
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

list=[], map={"": object} , set={}
EL에서 직접 컬레션을 만드는 일은 없다. java에서 만든거 request.setAttribute해서 EL로 사용한다
<%
	List<ProdVO> prodList = ProductList.getProductList();
	List<ProdVO> prodList2 = new ArrayList<>();
	request.setAttribute("prodList", prodList);
	request.setAttribute("prodList2", prodList2);


%>

<pre>
list : ${ [1, 3, 5, 7, 9] }
map  : ${{"a":1, "b": 2, "c":3 }}
set  : ${  {1, 3, 5, 7, 9} }

empty는 보통 사용하는데, 컬렉션이 null이면 true, 값이 없어도 true

${empty prodList }   	 ${empty prodList2 }
${not empty prodList }   	 ${not empty prodList2 }
</pre>

</body>
</html>