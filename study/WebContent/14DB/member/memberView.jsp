
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
<%
	String memId = request.getParameter("memId");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	try{
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT                                              ");
		sb.append("       mem_id      , mem_pass       , mem_name       ");
		sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir     ");
		sb.append("     , mem_zip     , mem_add1       , mem_add2       ");
		sb.append("     , mem_hp      , mem_mail       , mem_job        ");
		sb.append("     , mem_hobby   , mem_mileage    , mem_del_yn     ");
		sb.append(" FROM                                                ");
		sb.append("     member                                          ");
		sb.append(" WHERE     mem_id= ?                                 ");
		
		pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, memId);
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


		<div class="alert alert-warning">?????? ????????? ?????? ??? ????????????</div>
		<a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;??????
		</a>



		<div class="container">
			<h3>????????????</h3>
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th>?????????</th>
						<td>${member.memId }</td>
					</tr>
					<tr>
						<th>????????????</th>
						<td>${member.memPass }</td>
					</tr>
					<tr>
						<th>?????????</th>
						<td>${member.memName }</td>
					</tr>
					<tr>
						<th>????????????</th>
						<td>${member.memZip }</td>
					</tr>
					<tr>
						<th>??????</th>
						<td>${member.memAdd1 }
							 ${member.memAdd2 }
						</td>
					</tr>
					<tr>
						<th>??????</th>
						<td><input type="date" name="memBir" class="form-control input-sm" value='${member.memBir }' readonly="readonly"></td> <!-- 'YYYY-MM-DD'????????? value????????? ????????????????????? -->
					</tr>
					<tr>
						<th>?????????</th>
						<td>${member.memHp }</td>
					</tr>
					<tr>
						<th>??????</th>
						<td>${member.memJob }</td>
					</tr>
					<tr>
						<th>??????</th>
						<td>${member.memHobby }</td>
					</tr>
					<tr>
						<th>????????????</th>
						<td>${member.memMileage }</td>
					</tr>
					<tr>
						<th>????????????</th>
						<td>${member.memDelYn }</td>
					</tr>
					<tr>
						<td colspan="2"><a href="memberList.jsp" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list" aria-hidden="true"></span> &nbsp;??????
						</a> <a href='memberEdit.jsp?memId=${member.memId }' class="btn btn-info btn-sm"> <span class="glyphicon glyphicon-king" aria-hidden="true"></span> &nbsp;??????
						</a></td>
					</tr>
				</tbody>
			</table>
		</div>


</body>
</html>