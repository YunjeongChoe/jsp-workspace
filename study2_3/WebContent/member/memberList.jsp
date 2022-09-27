
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="ko">
<%request.setCharacterEncoding("utf-8"); %>
<head>
	<%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp"%>
<jsp:useBean id="searchVO" class="com.study.member.vo.MemberSearchVO"></jsp:useBean>
<jsp:setProperty property="*" name="searchVO"/>

<%
	IMemberService memberService = new MemberServiceImpl();
	List<MemberVO> memberList = memberService.getMemberList(searchVO);
	request.setAttribute("memberList", memberList);
	
	ICommCodeService codeService = new CommCodeServiceImpl();
	List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
	request.setAttribute("jobList", jobList);
	List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
	request.setAttribute("hobbyList", hobbyList);
	
%>
${searchVO }

 <div class="container">	
	<h3>회원목록</h3>	
	
		<!-- START : 검색 폼  -->
		<div class="panel panel-default collapse in" id="id_search_area">
			<div class="panel-body">
				<form name="search" action="memberList.jsp" method="post" class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage }"> <input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2">
							<select id="id_searchType" name="searchType" class="form-control input-sm">
								<option value="NM" ${searchVO.searchType eq "NM" ? "selected='selected'" : "" }>이름</option>
								<option value="ID" ${searchVO.searchType eq "ID" ? "selected='selected'" : "" }>아이디</option>
								<option value="HP" ${searchVO.searchType eq "HP" ? "selected='selected'" : "" }>핸드폰번호</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord" class="form-control input-sm" value="${searchVO.searchWord }" placeholder="검색어">
						</div>
						<div>
							<label for="id_searchJob" class="col-sm-1 col-sm-offset control-label">직업</label>
							<div class="col-sm-2">
								<select id="id_searchJob" name="searchJob" class="form-control input-sm">
									<option value="${searchVO.searchJob }">-- 전체 --</option>
									<c:forEach items="${jobList}" var="code">
										<option value="${code.commCd}"${searchVO.searchJob eq code.commCd ? "selected='selected'" : ""  } >${code.commNm}</option>
									</c:forEach>
								</select>
							</div>
							<label for="id_searchHobby" class="col-sm-1 col-sm-offset control-label">취미</label>
							<div class="col-sm-2">
								<select id="id_search" name="searchHobby" class="form-control input-sm">
									<option value="${searchVO.searchHobby }">-- 전체 --</option>
									<c:forEach items="${hobbyList}" var="code">
										<option value="${code.commCd}"${searchVO.searchHobby eq code.commCd ? "selected='selected'" : ""  } >${code.commNm}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-9 text-right">
							<button type="button" id="id_btn_reset" class="btn btn-sm btn-default">
								<i class="fa fa-sync"></i> &nbsp;&nbsp;초기화
							</button>
						</div>
						<div class="col-sm-1 text-right">
							<button type="submit" class="btn btn-sm btn-primary ">
								<i class="fa fa-search"></i> &nbsp;&nbsp;검 색
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- END : 검색 폼  -->
		
			<!-- START : 목록건수 및 새글쓰기 버튼  -->
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-sm-3 form-inline">
				전체 1000건 조회
				<select id="id_rowSizePerPage" name="rowSizePerPage" class="form-control input-sm">
					<c:forEach var="i" begin="10" end="50" step="10">
						<option value="${i }"${i eq searchVO.rowSizePerPage ? "selected='selected'" : "" } >${i }</option>
					</c:forEach>
				</select>
			</div>
		</div>
<!-- END : 목록건수 및 새글쓰기 버튼  -->
	
		
	<div>
		<a href="memberForm.jsp" class="btn btn-primary btn-sm pull-right">회원 등록</a>
	</div>
	<table class="table table-striped table-bordered">
	<caption class="hidden">회원목록 조회</caption>
	<colgroup>
		<col style="width: 15%" />
		<col />
		<col style="width: 20%" />
		<col style="width: 20%" />
		<col style="width: 15%" />
		<col style="width: 15%" />
	</colgroup>
	<thead>
		<tr>
			<th>ID</th>
			<th>회원명</th>
			<th>HP</th>
			<th>생일</th>
			<th>직업</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList }" var="member">
			<tr>
				<td>${member.memId }</td>
				<td><a href="memberView.jsp?memId=${member.memId }">
				${member.memName }</a></td>
				<td>${member.memHp }</td>
				<td>${member.memBir }</td>
				<td>${member.memJobNm }</td>
				<td>${member.memMileage }</td>
			</tr>
		</c:forEach>

	</tbody>			
	</table>
	
	<!-- START : 페이지네이션  -->
		<nav class="text-center">
			<ul class="pagination">

				<!-- 첫 페이지  -->
				<li><a href="memberList.jsp?curPage=1" data-page="1"><span aria-hidden="true">&laquo;</span></a></li>


				<!-- 이전 페이지 -->
				<c:if test="${searchVO.curPage > searchVO.pageSize }">
					<li><a href="memberList.jsp?curPage=${searchVO.firstPage-1 }" data-page=""><span aria-hidden="true">&lt;</span></a></li>
				</c:if>

				<!-- 페이지 넘버링  -->
				<!-- 페이지 넘버링  -->
		       <c:forEach begin="${searchVO.firstPage }" end="${searchVO.lastPage }" var="i">
				<c:if test="${searchVO.curPage == i }">
				<li class="active"><a href="#">${i }</a></li>
				</c:if>
				<c:if test="${searchVO.curPage != i }">
				<li><a href="freeList.jsp?curPage=${i }" data-page="${i }">${i }</a></li>
				</c:if>
				</c:forEach>
				

				<!-- 다음  페이지  -->
				<c:if test="${searchVO.lastPage != searchVO.totalRowCount }">
					<li><a href="memberList.jsp?curPage=${searchVO.lastPage+1 }" data-page=""><span aria-hidden="true">&gt;</span></a></li>
				</c:if>

				<!-- 마지막 페이지 -->
				<li><a href="memberList.jsp?curPage=${searchVO.totalRowCount }" data-page=""><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
		<!-- END : 페이지네이션  -->
	
	
</div>

</body>

 <script type="text/javascript">
	//form태그 안에 있는 input들은 submit하는 순간 파라미터가 한번에 넘어감 . 
	//name이랑 VO의 필드(속성, 멤버변수)랑 같아야 한다.

		// 변수 정의
		$form=$("form[name='search']");
		$curPage=$form.find("input[name='curPage']");
		

		// 각 이벤트 등록 >> a태그(get방식)
		// 페이지 링크 클릭, event전파방지, data속성값읽고 form태그 내의 input name=curPage값 바꾸기 
		//submit
		$('ul.pagination li a[data-page]').click(function(e) {
			//a태그 기본 이벤트 막기
			//form태그 안에 있는 curPage값을 내가 현재 누른 페이지 $("selector").data("page");로 바꾸기
			//그리고 form태그를 submit 
			e.preventDefault();
			$curPage.val(    $(this).attr("data-page")       );
			$form.submit();
			
			
			
			
			
			
		}); // ul.pagination li a[data-page]

		
		//form태그내의 버튼 클릭
		//이벤트전파방지, curPage 값 1로
		//submit
		$form.find("button[type=submit]").click(function(e) {
			e.preventDefault();
			$curPage.val(1);
			$form.submit();
		});
		
		

		
 		// 목록 갯수 변경
		// id_rowSizePerPage 변경되었을 때
		// 페이지 1, 목록수 = 현재값 으로 변경 후 서브밋
		$('#id_rowSizePerPage').change(function(e) {
			$curPage.val(1);
			$("form input[name='rowSizePerPage']").val($('#id_rowSizePerPage option:selected').val());
			$().val("rowSizePerPage");
			$form.submit();
		
		}); // '#id_rowSizePerPage'.change 
		// 초기화 버튼 클릭
			$('#id_btn_reset').click(function() {
			$curPage.val(1);
			$form.find("input[name='searchWord']").val("");
			$form.find("select[name='searchType'] option:eq(0)").attr("selected", "selected");
			$form.find("select[name='searchJob'] option:eq(0)").attr("selected", "selected");
			$form.find("select[name='searchHobby'] option:eq(0)").attr("selected", "selected");
			
			
		}); // #id_btn_reset.click 
		
	</script> 

</html>