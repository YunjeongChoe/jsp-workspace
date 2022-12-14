
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.ICommCodeService"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<div class="page-header">
			<h3>
				자유게시판 - <small>글 수정</small>
			</h3>
		</div>
		<form:form action="freeModify.wow" method="post"
			modelAttribute="freeBoard" enctype="multipart/form-data">
			<table class="table table-striped table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th>글번호</th>
					<td>${freeBoard.boNo }<form:hidden path="boNo" />
					</td>

				</tr>
				<tr>
					<th>제목</th>
					<td><form:input path="boTitle"
							cssClass="form-control input-sm" /> <form:errors path="boTitle" />
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${freeBoard.boWriter }<form:hidden path="boWriter" /> <form:errors
							path="boWriter" />

					</td>
				</tr>
				<tr>
					<th>비밀번호(사용자가 입력)</th>
					<td><form:password path="boPass"
							cssClass="form-control input-sm" title="알파벳 또는 숫자로 4글자 이상" /> <form:errors
							path="boPass" /> <span class="text-danger"> <span
							class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							글 등록시에 입력한 비밀번호를 입력하세요.
					</span></td>
				</tr>
				<tr>
					<th>분류</th>
					<td><form:select path="boCategory"
							cssClasss="form-control input-sm">
							<form:option value=""> --선택하세요--</form:option>
							<form:options items="${cateList}" itemLabel="commNm"
								itemValue="commCd" />
						</form:select> <form:errors path="boCategory" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><form:textarea path="boContent"
							cssClass="form-control input-sm" rows="10" /> <form:errors
							path="boContent" /></td>
				</tr>

				<tr>
					<th>조회수</th>
					<td>${freeBoard.boHit }</td>
				</tr>
				<tr>
					<th>최근등록일자</th>
					<td>${freeBoard.boModDate ne null 
								? freeBoard.boModDate
								: freeBoard.boRegDate 
						 }</td>
				</tr>
				<tr>
				<tr>
					<th>첨부파일
						<button type="button" id="id_btn_new_file">추가</button>
					</th>
					<td class="file_area"><c:forEach var="f"
							items="${freeBoard.attaches}" varStatus="st">
							<div>
								# 파일 ${st.count} <a
									href="<c:url value='/attach/download/${f.atchNo}' />"
									target="_blank"> <span class="glyphicon glyphicon-save"
									aria-hidden="true"></span> ${f.atchOriginalName}
								</a> Size : ${f.atchFancySize} Down : ${f.atchDownHit}
								<button class="btn_file_delete" data-atch-no="${f.atchNo}">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								</button>
							</div>
						</c:forEach>
						<div class="form-inline">
							<input type="file" name="boFiles" class="form-control">
							<button type="button" class="btn_delete btn btn-sm">삭제</button>
						</div></td>
				</tr>
				<td colspan="2">
					<div class="pull-left">
						<a href="freeList.wow" class="btn btn-default btn-sm"> <span
							class="glyphicon glyphicon-list" aria-hidden="true"></span>
							&nbsp;&nbsp;목록
						</a>
					</div>
					<div class="pull-right">

						<button type="submit" class="btn btn-sm btn-primary">
							<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
							&nbsp;&nbsp;저장
						</button>

						<button type="submit" formaction="freeDelete.wow"
							class="btn btn-sm btn-danger">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							&nbsp;&nbsp;삭제
						</button>


					</div>
				</td>
				</tr>
			</table>
		</form:form>

	</div>
	<!-- container -->
	<script type="text/javascript">
		// 첨부파일 추가버튼 클릭 
		$('#id_btn_new_file')
				.click(
						function() {
							$('.file_area')
									.append(
											'<div class="form-inline">'
													+ '<input type="file" name="boFiles" class="form-control">'
													+ ' <button type="button" class="btn_delete btn btn-sm">삭제</button>'
													+ '</div>');
						}); // #id_btn_new_file.click

		// 상위객체를 통해 이벤트 위임  
		$('.file_area').on('click', '.btn_delete', function() {
			$(this).closest('div').remove();
		});

		// 기존 첨부파일 삭제 클릭 
		$('.btn_file_delete').click(
				function() {
					$btn = $(this);
					$btn.closest('div').html(
							'<input type="hidden" name="delAtchNos" value="'
									+ $btn.data("atch-no") + '" />');
				}); //
	</script>


</body>
</html>


