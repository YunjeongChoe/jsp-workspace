<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
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

memberList id, 이름, 생일, 직업, 취미만 보여주기
list에서 view갈 때 이름을 클릭하게. 파라미터 이름은 memId
memberView에서는 전부 보여주기

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
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT                                              ");
		sb.append("     mem_id      , mem_pass     , mem_name           ");
		sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir     ");
		sb.append("     , mem_zip   , mem_add1     , mem_add2           ");
		sb.append("     , mem_hp    , mem_mail     , mem_job            ");
		sb.append("     , mem_hobby , mem_mileage  , mem_del_yn         ");
		sb.append(" FROM                                                ");
		sb.append("     member                                          ");
		
		rs = stmt.executeQuery(sb.toString());   //쿼리 수행. rs는 쿼리수행 결과 저장 객체(rs는 select에만)
		List<MemberVO> memberList = new ArrayList<>();
		while(rs.next()){
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

			memberList.add(member);
		}
		request.setAttribute("memberList", memberList);
		
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
			<td>ID</td>	
			<td>이름</td>	
			<td>생일</td>	
			<td>직업</td>	
			<td>취미</td>	
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList }" var="member">
			<tr>
				<td>${member.memId }</td>
				<td><a href="01memberView.jsp?memId=${member.memId }">${member.memName }
					</a>
				</td>
				<td>${member.memBir }</td>
				<td>${member.memJob }</td>
				<td>${member.memHobby }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>