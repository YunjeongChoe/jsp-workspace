<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>회원가입</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<form:form modelAttribute="member" action="regist.wow" method="post">
			<div class="row col-md-8 col-md-offset-2">
				<div class="page-header">
					<h3>회원가입</h3>
				</div>
				<table class="table">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>ID</th>
						<td><form:input path="memId" /> <form:errors path="memId" />
							<button onclick="return false;" type="button" id="idCheck" class="btn btn-outline-primary">중복확인</button>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><form:input path="memPass" type="password"/> <form:errors
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
							<button type="button" id="mailAuth" class="btn btn-outline-primary">이메일인증하기</button></td>
					</tr>
					<tr class="form-group-sm">
					<th>생일</th>
					<td>
						<input type="date" name="memBir" value="${member.memBir }"/>
						<form:errors path="memBir"/>
					</td>
				</tr>
				<tr class="form-group-sm">
					<th>핸드폰</th>
					<td>
						<form:input path="memHp"/>
						<form:errors path="memHp"/>
					</td>
				</tr>
				
				<tr>
					<th>취미</th>
					<td class="form-group-sm">
						<form:select path="memHobby">
								<form:option value="">--선택하세요--</form:option>
								<form:options items="${hobbyList }" itemLabel="cateNm" itemValue="cateCd"/>
							</form:select>
					</td>
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
										aria-hidden="true"></span>가입하기
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
			url : "<c:url value='/join/idCheck.wow' />",
			data : {"id" : id},
			success : function(data) {
				if(data=='success'){
					alert("사용가능한 아이디");
					isIdChecked = true;				
				}
				if(data=='fail'){
					alert("중복된 아이디");
					isIdChecked = false;
				}
			},
			error : function(req, st, err){
					console.log(req);
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
                    $("#mailAuthKey").val(data);
			           var opener= window.open("<c:url value='mailWindow.wow'/>","메일인증","_blank,  width=500px, height=200px,left=500px,top=200px");
							
							    	/* opener.close(); */
							  
			        },error : function(req,st,err){
			            console.log(req);
			        }
			    });//ajax
			});//mailCheck
        // 다음 버튼(form submit)을 눌렀을 때 idCheck를 했다면 넘어가고
		// 안했다면 alert("idCheck 안했습니다")
		// submit 바로 하면 x, 일단 e.preventDefault()
		// submit해도 되면 submit() 함수
			$("button[type='submit']").on("click",function(e) {
			e.preventDefault();
			if(isIdChecked){
				$("form").submit();
			}else{
				alert("아이디 중복확인해주세요");
			}
		});
			
		$("input[name='memId']").on("change", function(e){
			isIdChecked = false;
		}); // id 변경될때마다
		
	}); // ready
</script>

</html>