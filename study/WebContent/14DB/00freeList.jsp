<%@page import="java.util.ArrayList"%>
<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
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
	//1. (oracle)드라이버 로드   2. 실제 DB에 연결   3. 쿼리 수행   4.연결종료
	//
	
	Class.forName("oracle.jdbc.driver.OracleDriver");  //Class.forName 런타임 동적로딩	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try{
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jsp","oracle");
		//2번 연결
		
		//3.쿼리 수행
		stmt = conn.createStatement();   //쿼리수행 객체
		String queryString = "";
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT                                                     ");
		sb.append("       bo_no         , bo_title, bo_category                ");
		sb.append("     , bo_writer     , bo_pass , bo_content                 ");
		sb.append("     , bo_hit                                               ");
		sb.append("     , TO_CHAR(bo_reg_date, 'YYYY-MM-dd') as bo_reg_date    ");
		sb.append("     , TO_CHAR(bo_mod_date, 'YYYY-MM-dd') as bo_mod_date    ");
		sb.append("     , bo_del_yn                                            ");
		sb.append(" FROM                                                       ");
		sb.append("     free_board                                             ");
		rs = stmt.executeQuery(sb.toString());   //쿼리 수행. rs는 쿼리수행 결과 저장 객체(rs는 select에만)
		List<FreeBoardVO> freeBoardList = new ArrayList<>();
		while(rs.next()){
			FreeBoardVO freeBoard = new FreeBoardVO();
			freeBoard.setBoNo(rs.getInt("bo_no"));
			freeBoard.setBoTitle(rs.getString("bo_title"));
			freeBoard.setBoCategory(rs.getString("bo_category"));
			freeBoard.setBoWriter(rs.getString("bo_writer"));
			freeBoard.setBoPass(rs.getString("bo_pass"));
			freeBoard.setBoContent(rs.getString("bo_content"));
			freeBoard.setBoHit(rs.getInt("bo_hit"));
			freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
			freeBoard.setBoModDate(rs.getString("bo_mod_date"));
			freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
			freeBoardList.add(freeBoard);
		}
		request.setAttribute("freeBoardList", freeBoardList);
		
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null)   { try{rs.close();}   catch(Exception e){}}
		if(stmt!=null) { try{stmt.close();} catch(Exception e){}}
		if(conn!=null) { try{conn.close();} catch(Exception e){}}
	}
%>

<table class="table table-striped">
	<thead>
		<tr>
			<td>글번호</td>	
			<td>글제목</td>	
			<td>글작성자</td>	
			<td>글작성일</td>	
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${freeBoardList }" var="freeBoard">
			<tr>
				<td>${freeBoard.boNo }</td>
				<td><a href="00freeview.jsp?boNo=${freeBoard.boNo }">${freeBoard.boTitle }
					</a>
				</td>
				<td>${freeBoard.boWriter }</td>
				<td>${freeBoard.boRegDate }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>