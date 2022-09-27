
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<%
	request.setCharacterEncoding("utf-8");
%>

<%@include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div class="container">
		<div class="page-header">
			<h3>
				요리시간 - <small>신나는 요리시간!</small>
			</h3>
		</div>

		<!-- START : 검색 폼  -->

		<div class="panel panel-default">
			<div class="panel-body">
				<!--form태그안에서 EL을 사용해서 input들 내용이 유지되게  -->
				<form name="search" action="recipe.wow" method="post"
					class="form-horizontal">
					<input type="hidden" name="curPage" value="${searchVO.curPage }">
					<input type="hidden" name="rowSizePerPage"
						value="${searchVO.rowSizePerPage }">
					<div class="form-group">

						<div class="col-sm-2" >
							<input type="text" name="searchWord" 
								class="form-control input-sm" value="${searchVO.searchWord }"
								placeholder="검색어를 입력하세요">
							<button type="submit" class="btn btn-outline-secondary" style="display: inline-block;" >
								검색</button>
						</div>

					</div>

				</form>

			</div>
		</div>
		<!-- END : 검색 폼  -->

		<table class="table table-striped table-bordered table-hover">
			<colgroup>
				<col width="10%" />
				<col />
				<col width="20%" />
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${recipeList }" var="recipe">
					<tr>
						<td class="text-center" style="vertical-align: middle;">${recipe.recNo }</td>
						<td><a
							href="javascript:void(window.open('${recipe.recUrl }', '네이버 블로그','width=1010, height=756'))">
								${recipe.recTitle }</a> <br>${recipe.recContent }</td>
						<td><img
							src="<%=request.getContextPath() %>/resources/images/img_download/${recipe.recNo }.jpg"></td>


					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- START : 페이지네이션  -->
		<div class="nav justify-content-center">
		<nav class="pagination">
			<ul class="nav justify-content-center bg-light">
				<!-- 첫 페이지  -->
				<c:if test="${searchVO.firstPage != 1 }">
					<li><a class="page-link" href="recipe.wow?curPage=1"
						data-page="1"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<!-- 이전 페이지 -->
				<c:if test="${searchVO.firstPage!=1 }">
					<li><a class="page-link"
						href="recipe.wow?curPage=${searchVO.firstPage-1 }"
						data-page="${searchVO.firstPage-1 }"><span aria-hidden="true">&lt;</span></a></li>
				</c:if>

				<!-- 페이지 넘버링  -->
				<c:forEach begin="${searchVO.firstPage }"
					end="${searchVO.lastPage }" var="i">
					<c:if test="${searchVO.curPage !=i }">
						<li class="page-item"><a class="page-link"
							href="recipe.wow?curPage=${i }" data-page="${i }">${i }</a></li>
					</c:if>
					<c:if test="${searchVO.curPage ==i }">
						<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
					</c:if>
				</c:forEach>

				<!-- 다음  페이지  -->
				<c:if test="${searchVO.lastPage!=searchVO.totalPageCount }">
					<li><a class="page-link"
						href="recipe.wow?curPage=${searchVO.lastPage+1 }"
						data-page="${searchVO.lastPage+1 }"><span aria-hidden="true">&gt;</span></a></li>
				</c:if>

				<!-- 마지막 페이지 -->
				<c:if test="${searchVO.lastPage != searchVO.totalPageCount }">
					<li><a class="page-link"
						href="recipe.wow?curPage=${searchVO.totalPageCount }"
						data-page="${searchVO.totalPageCount }"><span
							aria-hidden="true">&raquo;</span></a></li>
				</c:if>
			</ul>
		</nav>
		</div>
		<!-- END : 페이지네이션  -->



	</div>
	<!-- container -->
</body>
<script type="text/javascript">
	// 변수 정의
	$form = $("form[name='search']");
	$curPage = $form.find("input[name='curPage']");
	// 각 이벤트 등록

	// 페이지 링크 클릭
	$('ul.pagination li a[data-page]').click(function(e) {
		e.preventDefault();
		//이벤트 전파 방지,  <a>의 기본 클릭이벤트를 막기위함
		$curPage.val($(this).data('page'));
		$form.submit();
		// data-page에 있는 값을 input태그 중 이름이 curPage인 것의 값으로 바꾸고 서브밋

	}); // ul.pagination li a[data-page] 끝 

	// 검색 클릭 
	$form.find("button[type=submit]").click(function(e) {
		// 이벤트 전파 방지
		e.preventDefault();
		// 페이지 1로 재설정
		$curPage.val(1);
		// 서브밋
		$form.submit();

	}); // button[type=submit]").click 끝
</script>
</html>






