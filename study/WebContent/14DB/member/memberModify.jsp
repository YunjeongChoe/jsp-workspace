
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="member" class="com.study.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb = new StringBuffer();
		sb.append(" UPDATE member SET         ");
		sb.append("       mem_pass=?          ");
		sb.append("     , mem_name=?          ");
		sb.append("     , mem_zip=?           ");
		sb.append("     , mem_add1=?          ");
		sb.append("     , mem_add2=?          ");
		sb.append("     , mem_bir=?           ");
		sb.append("     , mem_mail=?          ");
		sb.append("     , mem_hp=?            ");
		sb.append("     , mem_job=?           ");
		sb.append("     , mem_hobby=?         ");
		sb.append(" WHERE mem_id =?          ");

		pstmt = conn.prepareStatement(sb.toString());
		int count = 1;
		pstmt.setString(count++, member.getMemPass());
		pstmt.setString(count++, member.getMemName());
		pstmt.setString(count++, member.getMemZip());
		pstmt.setString(count++, member.getMemAdd1());
		pstmt.setString(count++, member.getMemAdd2());
		pstmt.setString(count++, member.getMemBir());
		pstmt.setString(count++, member.getMemMail());
		pstmt.setString(count++, member.getMemHp());
		pstmt.setString(count++, member.getMemJob());
		pstmt.setString(count++, member.getMemHobby());
		pstmt.setString(count++, member.getMemId());
	
		int cnt = pstmt.executeUpdate();
		
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
		if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
		if(conn!=null) { try{conn.close();} catch(Exception e){}}
	}
%>

	<div class="container">
		<h3>????????????</h3>
		
			<div class="alert alert-success">??????????????? ??????????????????.</div>
		
			<div class="alert alert-warning">??????????????? ?????? ??? ???????????????</div>
		
			<div class="alert alert-warning">????????????</div>
	

		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;??????
		</a> <a href="memberView.jsp?memId=${member.memId }" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;?????? ???
		</a>

	</div>
</body>
</html>