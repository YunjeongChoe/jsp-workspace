<%@page import="com.study.common.vo.ProdVO"%>
<%@page import="com.study.common.util.ProductList"%>
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

html은 html태그 + JS, CSS
<hr>
<%
	List<ProdVO> prodList = ProductList.getProductList();
	ProdVO prod = prodList.get(0);
	request.setAttribute("prod", prod);
%>
<input type="text" name="prodId" value="<%=prod.getProdId() %>">
<button type="button" onclick="button()">button</button>
</body>
<script type="text/javascript">
	function button() {}
	<%-- var prod = <%=prod.getProdId()%>; 에러남--%>
	var prod ="<%=prod.getProdId()%>";
	var prodId = ${prod.prodId};
	var prodId = "${prod.prodId}";
	
	alert(prodId);
	};
</script>

</html>