<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
	String memId = request.getParameter("memId");
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jsp","oracle");

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                              ");
		sb.append("     mem_id      , mem_pass     , mem_name           ");
		sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir     ");
		sb.append("     , mem_zip   , mem_add1     , mem_add2           ");
		sb.append("     , mem_hp    , mem_mail     , mem_job            ");
		sb.append("     , mem_hobby , mem_mileage  , mem_del_yn         ");
		sb.append(" FROM                                                ");
		sb.append("     member                                          ");
		sb.append(" WHERE mem_id = ?                                   " );
		
		pstmt = conn.prepareStatement(sb.toString());	
		//쿼리문이 ? 포함 된 쿼리문. ? 처리를 하고나서 executeQuery()해야 됨
				
		pstmt.setString(1, memId);            //? 처리할 때 '' 알아서 처리해줌
	
		rs = pstmt.executeQuery();
		if(rs.next()){
			MemberVO member = new MemberVO();
			member.setMemId(rs.getString("mem_id"));
			member.setMemPass(rs.getString("mem_pass"));
			member.setMemName(rs.getString("mem_name"));
			member.setMemBir(rs.getString("mem_bir"));
			member.setMemZip(rs.getString("mem_zip"));
			member.setMemAdd1(rs.getString("mem_add1"));
			member.setMemAdd2(rs.getString("mem_add2"));
			member.setMemHp(rs.getString("mem_hp"));
			member.setMemMail(rs.getString("mem_mail"));
			member.setMemJob(rs.getString("mem_job"));
			member.setMemHobby(rs.getString("mem_hobby"));
			member.setMemMileage(rs.getInt("mem_mileage"));
			member.setMemDelYn(rs.getString("mem_del_yn"));


		request.setAttribute("member", member);
		}
		
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
		if(pstmt!=null) { try{pstmt.close();} catch(Exception e){}}
		if(conn!=null) { try{conn.close();} catch(Exception e){}}
	}
%>


<table border="1">
	<tr>
		<td>아이디</td>
		<td>${member.memId }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${member.memPass }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${member.memName }</td>
	</tr>
	<tr>
		<td>생일</td>
		<td>${member.memBir }</td>
	</tr>
	<tr>
		<td>우편번호</td>
		<td>${member.memZip }</td>
	</tr>
	<tr>
		<td>주소1</td>
		<td>${member.memAdd1 }</td>
	</tr>
	<tr>
		<td>주소2</td>
		<td>${member.memAdd2 }</td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>${member.memHp }</td>
	</tr>
	<tr>
		<td>메일</td>
		<td>${member.memMail }</td>
	</tr>
		<tr>
		<td>직업</td>
		<td>${member.memJob }</td>
	</tr>
	<tr>
		<td>취미</td>
		<td>${member.memHobby }</td>
	</tr>
		<tr>
		<td>마일리지</td>
		<td>${member.memMileage }</td>
	</tr>
	<tr>
		<td>삭제여부</td>
		<td>${member.memDelYn }</td>
	</tr>

	

</table>









</body>
</html>