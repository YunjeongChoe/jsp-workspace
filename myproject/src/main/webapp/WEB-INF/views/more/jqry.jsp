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
<style>
    #layerTest {
    	display: none;
    }
</style>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>

<div>
    <a href="javascript:;" id="myTest" title="새창 열림" onclick="lyOpen()">테스트</a>
    <input type="text" id="myTextField1" value="Text field1">    
    <input type="text" id="myTextField2" value="Text field2">
</div>
<div id="layerTest">
    <p>레이어 테스트. 디자인은 무시합니다.</p>
    <a href="javascript:;" id="myClose" onclick="lyClose()">닫기</a>
</div>
<div>
    <input type="text" id="myTextField3" value="Text field3">
</div>
<script> 
   
    function lyOpen(){
        $("#layerTest").show();
        $("#myClose").focus(); //주석처리 시 myTextField1, myTextField2 지난 후 레이어로 이동
    }
   
    function lyClose(){
    	$("#layerTest").hide();
        $("#myTest").focus(); //주석처리 시 myTextField3로 이동
    }
   
</script>
</body>
</html>