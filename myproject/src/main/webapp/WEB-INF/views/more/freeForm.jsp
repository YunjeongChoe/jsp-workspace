<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommCodeService"%>
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
				자유게시판 - <small>글 등록</small>
			</h3>
		</div>
		<form:form action="freeRegist.wow" method="post"
			modelAttribute="freeBoard" enctype="multipart/form-data">
			<table class="table table-striped table-bordered">
				<colgroup>
					<col width="20%" />
					<col />
				</colgroup>
				<tr>
					<th>제목</th>
					<td><form:input path="boTitle"
							cssClass="form-control input-sm" /> <form:errors path="boTitle" />
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><form:input path="boWriter" cssClass="form-control input-sm"/> <form:errors
							path="boWriter" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><form:password path="boPass"
							cssClass="form-control input-sm" title="알파벳과 숫자로 4글자 이상 입력" /> <form:errors
							path="boPass" /> <span class="text-danger"> <span
							class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							수정 또는 삭제시에 필요합니다.
					</span></td>
				</tr>
				<tr>
					<th>분류</th>
					<td><form:select path="boCategory"
							class="form-select">
							<form:option value=""> -- 선택하세요 --</form:option>
							<form:options items="${cateList }" itemLabel="cateNm"
								itemValue="cateCd" />
						</form:select> <form:errors path="boCategory" /></td>
				</tr>

				<tr>
					<th>내용</th>
					<td><form:textarea path="boContent" cssClass="form-control"
							rows="10" /> <form:errors path="boContent" /></td>
				</tr>

				<tr>
					<th>첨부파일
						<button type="button" class="btn btn-info" id="id_btn_new_file">추가</button>
					</th>
					<td class="file_area">
						<div class="form-inline">
							<input type="file" name="boFiles" class="form-control">
							<button type="button" class="btn_delete btn btn-sm">삭제</button>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="pull-left">
							<a href="freeList.wow" class="btn btn-outline-dark"> <span
								class="glyphicon glyphicon-list" aria-hidden="true"></span>
								목록
							</a>
							<button type="submit" class="btn btn-outline-success">
								<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
								저장
							</button>
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	<!-- container -->
	<script type="text/javascript">
	$('#id_btn_new_file').click(function(){ 
		$('.file_area').append('<div class="form-inline">' 
		+ '<input type="file" name="boFiles" class="form-control">' 
		+ ' <button type="button" class="btn_delete btn btn-sm">삭제</button>' + '</div>'); 
		}); 
		
	$('.file_area').on('click','.btn_delete', function(){ 
		$(this).closest('div').remove(); 
		});
	</script>
</body>
</html>


