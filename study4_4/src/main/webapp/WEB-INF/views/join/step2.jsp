<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>회원가입 2단계</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<form:form modelAttribute="member" action="step3.wow" method="post">
			<div class="row col-md-8 col-md-offset-2">
				<div class="page-header">
					<h3>회원가입 2단계</h3>
				</div>
				<table class="table">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>ID</th>
						<td><form:input path="memId" /> <form:errors path="memId" />
							<button onclick="return false;" type="button" id="idCheck">중복확인</button>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><form:input path="memPass" /> <form:errors
								path="memPass" /></td>
					</tr>

					<tr class="form-group-sm">
						<th>회원명</th>
						<td><form:input path="memName" /> <form:errors
								path="memName" /></td>
					</tr>
					<tr class="form-group-sm">
						<th>이메일</th>
						<td><form:input path="memMail" /> <form:errors
								path="memMail" />
							<button type="button" id="mailAuth">이메일인증하기</button></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="pull-left">
								<a href="cancel" class="btn btn-sm btn-default"> <span
									class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									&nbsp;&nbsp;취 소
								</a>
							</div>
							<div class="pull-right">
								<button type="submit" class="btn btn-sm btn-primary">
									<span class="glyphicon glyphicon-chevron-right"
										aria-hidden="true"></span> &nbsp;&nbsp;다 음
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form:form>
	</div>
	<!-- END : 메인 콘텐츠  컨테이너  -->
</body>
<script type="text/javascript">
var isCheck= false;
var isMailAuthed= false;
$(document).ready(function () {
	$("#idCheck").on("click", function(e) {
		e.preventDefault();
		var id= $("input[name='memId']").val();
		$.ajax({
//			url : "<c:url value='/join/step2_check.wow'/>",
 				url : "${pageContext.request.contextPath}/join/step2_check.wow",
			data : {"id" : id},
			success : function(data) {
				if(data=='S'){
					alert("사용가능한 아이디");
					isCheck=true;
				}
				if(data=='F'){
					alert("중복된 아이디");
					isCheck=false;
				}
			},
			error : function(e) {
				alert("error");
			}
		}); // ajax
	}); // idCheck
	var isCheck= false;
	var isMailAuthed= false;
	$(document).ready(function () {
		$("#idCheck").on("click", function(e) {
			e.preventDefault();
			var id= $("input[name='memId']").val();
			$.ajax({
//				url : "<c:url value='/join/step2_check.wow'/>",
	 				url : "${pageContext.request.contextPath}/join/step2_check.wow",
				data : {"id" : id},
				success : function(data) {
					if(data=='S'){
						alert("사용가능한 아이디");
						isCheck=true;
					}
					if(data=='F'){
						alert("중복된 아이디");
						isCheck=false;
					}
				},
				error : function(e) {
					alert("error");
				}
			}); // ajax
		}); // idCheck
		
		// 다음버튼 (form submit)눌렀을 때 idCheck를 했다면 넘어감
		// 안했다면 alert("중복체크를 해주세요");
		// submit 바로하면 안되니까
			//mail인증하기 버튼 클릭
			$("#mailAuth").on("click",function(e){
				e.preventDefault();
				isMailAuthed=true;
			    $.ajax({
			        url : "<c:url value='mailAuth.wow' />"
			        ,data : {"mail" : $("input[name='memMail']").val()}
			        ,success: function(data){
			           alert(data);
			           var opener= window.open("<c:url value='mailWindow.wow'/>","메일인증","_blank, width=500px, height=200px,left=500px,top=200px");
			           alert("종료");
			        },error : function(req,status,err){
			            console.log(req);
			        }
			    });//ajax
			});//mailCheck
			
			$("button[type='submit']").on("click",function(e) {
			// a태그 기본이벤트 대신 form태그 submit하면됨
			//$('selector').data('page');
			e.preventDefault();
			if(isCheck){
				$("form").submit();
			}else{
				alert("중복체크를 해주세요");
			}
		});
		
		$("input[name='memId']").on("change", function(e){
			isCheck = false;
		});
		
	}); // ready
	// 다음버튼 (form submit)눌렀을 때 idCheck를 했다면 넘어감
	// 안했다면 alert("중복체크를 해주세요");
	// submit 바로하면 안되니까
		//mail인증하기 버튼 클릭
		$("#mailAuth").on("click",function(e){
			e.preventDefault();
			isMailAuthed=true;
		    $.ajax({
		        url : "<c:url value='mailAuth.wow' />"
		        ,data : {"mail" : $("input[name='memMail']").val()}
		        ,success: function(data){
		           alert(data);
		           var opener= window.open("<c:url value='mailWindow.wow'/>","메일인증","_blank, width=500px, height=200px,left=500px,top=200px");
		           alert("종료");
		        },error : function(req,status,err){
		            console.log(req);
		        }
		    });//ajax
		});//mailCheck
		
		$("button[type='submit']").on("click",function(e) {
		// a태그 기본이벤트 대신 form태그 submit하면됨
		//$('selector').data('page');
		e.preventDefault();
		if(isCheck){
			$("form").submit();
		}else{
			alert("중복체크를 해주세요");
		}
	});
	
	$("input[name='memId']").on("change", function(e){
		isCheck = false;
	});
	
}); // ready
</script>

</html>