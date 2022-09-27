
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="member" class="com.study.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		//연결
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		
		//쿼리문
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO member (                          ");
		sb.append("    mem_id       , mem_pass    , mem_name     ");
		sb.append("    , mem_bir    , mem_zip     , mem_add1     ");
		sb.append("    , mem_add2   , mem_hp      , mem_mail     ");
		sb.append("    , mem_job    , mem_hobby   , mem_mileage  ");
		sb.append("    , mem_del_yn                              ");
		sb.append(") VALUES (                                     ");
		sb.append("    ?             ,?         ,?                ");
		sb.append("   ,?            ,?          ,?                ");
		sb.append("   ,?            ,?          ,?                ");
		sb.append("   ,?            ,?          ,0				  ");
		sb.append("   ,'N'											  ");
		sb.append(")     												  ");

pstmt= conn.prepareStatement(sb.toString());
		
		//?세팅, 쿼리실행
		int i = 1;
		pstmt.setString(i++, member.getMemId());
		pstmt.setString(i++, member.getMemPass());
		pstmt.setString(i++, member.getMemName());
		pstmt.setString(i++, member.getMemBir());
		pstmt.setString(i++, member.getMemZip());
		pstmt.setString(i++, member.getMemAdd1());
		pstmt.setString(i++, member.getMemAdd2());
		pstmt.setString(i++, member.getMemHp());
		pstmt.setString(i++, member.getMemMail());
		pstmt.setString(i++, member.getMemJob());
		pstmt.setString(i++, member.getMemHobby());

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
		<h3>회원등록</h3>
		
			<div class="alert alert-success">정상적으로 회원 등록 되었습니다.</div>
		
			<div class="alert alert-warning">아이디 중복</div>
			<a href="#" class="btn btn-default btn-sm" onclick="history.back();"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> &nbsp;뒤로가기
			</a>
	

		
			<div class="alert alert-warning">등록 실패 </div>
		

		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;목록
		</a>
	</div>
</body>
</html>