<%@page import="java.util.Enumeration"%>
<%@page import="com.study.exception.BizNotEffectedException"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
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
<%@ include file="/WEB-INF/inc/header.jsp"%>



<title>자유게시판 - 글 보기</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div class="container">
		<div class="page-header">
			<h3>
				자유게시판 - <small>글 보기</small>
			</h3>
		</div>
		<table class="table table-striped table-bordered">
			<tbody>
				<tr>
					<th>글번호</th>
					<td>${freeBoard.boNo }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${freeBoard.boTitle }</td>
				</tr>
				<tr>
					<th>글분류</th>
					<td>${freeBoard.boCategory }</td>
				</tr>
				<tr>
					<th>작성자명</th>
					<td>${freeBoard.boWriter }</td>
				</tr>
				<!-- 비밀번호는 보여주지 않음  -->
				<tr>
					<th>내용</th>
					<td>${freeBoard.boContent }
						<div id="uploadResult">
							<div id="result_card">
								<c:forEach var="f" items="${freeBoard.attaches}" varStatus="st">

									<img src="/home/attach/dwonload/${f.atchFileName}">
									<%-- <img src="${pageContext.request.contextPath}/resources/images/prod/CPU001.jpg"> --%>

								</c:forEach>
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<th>조회수</th>
					<td>${freeBoard.boHit }</td>
				</tr>
				<tr>
					<th>최근등록일자</th>
					<td>${freeBoard.boModDate ne null 
							? freeBoard.boModDate
							: freeBoard.boRegDate }
					</td>
				</tr>
				<tr>
					<th>삭제여부</th>
					<td>${freeBoard.boDelYn }</td>
				</tr>
				<tr>
				<tr>
					<th>첨부파일</th>
					<td><c:forEach var="f" items="${freeBoard.attaches}"
							varStatus="st">
							<div>
								파일 ${st.count} <a
									href="<c:url value='/attach/download/${f.atchNo}' />"
									target="_blank"> <span class="glyphicon glyphicon-save"
									aria-hidden="true"></span> ${f.atchOriginalName}
								</a> Size : ${f.atchFancySize} Down : ${f.atchDownHit}
							</div>
						</c:forEach></td>
				</tr>

				<tr>
					<td colspan="2">
						<div class="pull-left">
							<a href="freeList.wow" class="btn btn-default btn-sm"> <span
								class="glyphicon glyphicon-list" aria-hidden="true"></span>
								&nbsp;&nbsp;목록
							</a>
						</div>
						<div class="pull-right">
							<a href="freeEdit.wow?boNo=${freeBoard.boNo }"
								class="btn btn-success btn-sm"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								&nbsp;&nbsp;수정
							</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- container -->
	<div class="container">
		<!-- reply container -->
		<!-- // START : 댓글 등록 영역  -->
		<div class="panel panel-default">
			<div class="panel-body form-horizontal">
				<form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
					method="post" onclick="return false;">
					<input type="hidden" name="reParentNo" value="${freeBoard.boNo}">
					<input type="hidden" name="reCategory" value="FREE"> <input
						type="hidden" name="reMemId" value="${USER_INFO.userId }">
					<input type="hidden" name="reIp"
						value="<%=request.getRemoteAddr()%>">
					<div class="form-group">
						<label class="col-sm-2  control-label">댓글</label>
						<div class="col-sm-8">
							<textarea rows="3" name="reContent" class="form-control"
								${USER_INFO eq null ? "readonly='readonly'" : ""}></textarea>
						</div>
						<div class="col-sm-2">
							<button id="btn_reply_regist" type="button"
								class="btn btn-sm btn-info">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- // END : 댓글 등록 영역  -->


		<!-- // START : 댓글 목록 영역  -->
		<div id="id_reply_list_area">

			<div class="row">
				<div class="col-sm-2 text-right">홍길동</div>
				<div class="col-sm-6">
					<pre>내용</pre>
				</div>
				<div class="col-sm-2">12/30 23:45</div>
				<div class="col-sm-2">
					<button name="btn_reply_edit" type="button"
						class=" btn btn-sm btn-info">수정</button>
					<button name="btn_reply_delete" type="button"
						class="btn btn-sm btn-danger">삭제</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2 text-right">그댄 먼곳만 보네요</div>
				<div class="col-sm-6">
					<pre> 롤링롤링롤링롤링</pre>
				</div>
				<div class="col-sm-2">11/25 12:45</div>
				<div class="col-sm-2"></div>
				<button name="btn_reply_edit" type="button"
					class=" btn btn-sm btn-info" onclick="fn_modify()">수정</button>
				<button name="btn_reply_delete" type="button"
					class="btn btn-sm btn-danger">삭제</button>
			</div>
		</div>

		<div class="row text-center" id="id_reply_list_more">
			<a id="btn_reply_list_more"
				class="btn btn-sm btn-default col-sm-10 col-sm-offset-1"> <span
				class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
				더보기
			</a>
		</div>
		<!-- // END : 댓글 목록 영역  -->


		<!-- START : 댓글 수정용 Modal -->
		<div class="modal fade" id="id_reply_edit_modal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form name="frm_reply_edit"
						action="<c:url value='/reply/replyModify' />" method="post"
						onclick="return false;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h4 class="modal-title">댓글수정</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" name="reNo" value="">
							<textarea rows="3" name="reContent" class="form-control"></textarea>
							<input type="hidden" name="reMemId" value="${USER_INFO.userId }">
						</div>
						<div class="modal-footer">
							<button id="btn_reply_modify" type="button"
								class="btn btn-sm btn-info">저장</button>
							<button type="button" class="btn btn-default btn-sm"
								data-dismiss="modal">닫기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- END : 댓글 수정용 Modal -->

	</div>
	<!-- reply container -->
</body>
<script type="text/javascript">
	// 댓글 데이터를 딱 10개만 가지고 오도록 하는 파라미터 모음
    var params={"curPage":1, "rowSizePerPage" : 10 ,"reCategory" : "FREE", "reParentNo": ${freeBoard.boNo} };
            
	//ajax 요청해서 댓글리스트를 받아오는 함수.
    function fn_reply_list(){
		//아작스 호출해서 DB에 있는 reply 데이터 가지고 옵니다.
		//가지고오면(success)하면 댓글 div 만들어줍니다. 
		//list를 가지고오니까 jquery 반복문 써서 div 여러개 만들어주면됩니다.
        // 다 했으면 param의 curPage=2로 바꿔줍시다
        $.ajax({
        	url:"<c:url value='/reply/replyList.wow' />"
        	,data : params
        	,success:function(data){
        		//data가지고 댓글태그 만들기
        		//data가 자바에서 map형태였던 것 => json화 된 것 => data 
        		console.log(data);
        		//댓글목록 영역에 댓글 div 하나 만들어서 append.
        		var list = data.result;
        		$.each(list, function(index, element){
        			var str = "";
		     	str+= ' <div class="row" data-re-no="'+element.reNo +'"> ';
				str+= ' <div class="col-sm-2 text-right">' + element.reMemName + '</div> ';
				str+= ' <div class="col-sm-6"> ';
				str+= ' 	<pre>' + element.reContent + '</pre> ';
				str+= ' </div> ';
				str+= ' <div class="col-sm-2">' + element.reRegDate + '</div> ';
				str+= ' <div class="col-sm-2">';
				if(element.reMemId == '${USER_INFO.userId}' ){
					str+= ' 	<button name="btn_reply_edit" type="button" class="btn btn-sm btn-info" >수정</button> ';
					str+= ' 	<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger">삭제</button> ';
					}
					str+= ' </div>';
				str+= ' </div>';
				$('#id_reply_list_area').append(str);
        		}); //each
				params.curPage+=1;
        	} //success
        }); //ajax
        
        
    }//function fn_reply_list
    
       
    

    $(document).ready(function(){ //documnet가 준비될 때 
        //더보기 버튼
        $("#id_reply_list_more").on("click",function(e){
			//fn_reply_list에서 마지막에 curPage=2로 바꿔줍니다. 
            //그래서 그냥 fn_reply_list()하면 다음 댓글 10개 가져옵니다.
            e.preventDefault();
            fn_reply_list();
		});

        //등록버튼
        $("#btn_reply_regist").on("click",function(e){
			// form태그안에 input hidden으로 필요한거 넣기 (되어있음)
            //가장가까운 form찾은 후 ajax 호출(data는 form.serialize() => form의 객체들을 한번에 받기 위해, )
		    //성공 : 등록 글 내용부분 지우기,  댓글영역초기화( list_area.html(''), curPage=1, fn_reply_list)
           e.preventDefault();
           $form = $(this).closest("form[name='frm_reply']"); //이곳의 this는 on("click",function이거
           $.ajax({
	    		url:"<c:url value='/reply/replyRegist.wow' />"
	    		,data: $form.serialize()
	    		,success: function(data){
	    			//console.log(data);
	    			$form.find("textarea[name='reContent']").val(''); //지우기 (파라미터로 넘어가기 때문에 (form태그에서는)val을 씀 )
	    			//ajax의 this와 버튼이벤트의 this는 다르다
	    			$("#id_reply_list_area").html('');                //초기화
	    			params.curPage=1;
	    			fn_reply_list();	
	    		}
	    		,error: function(req,st,err){ //ajax로 받은 요청이 false이면 error로 옴
	    			console.log(req)
	    			//실패 : error : req.status==401이면 login으로   location.href
	    			if(req.status==401){
	    				location.href="<c:url value='/login/login.wow' />";
	    			}
	    		}
	    	});//ajax
        });//등록버튼

        
      	//수정버튼 : 댓글 영역안에 있는 수정버튼만  이벤트 등록 
        $("#id_reply_list_area").on("click", 'button[name="btn_reply_edit"]'
                ,function(e){
           //현재 버튼의 상위 div(한개 댓글) 찾기
 	       //div에서 현재 댓글 내용을 modal에 있는 textarea에 복사
	       //div태그의 data-re-no 값을 modal에 있는 input name="reNo" 태그의 value값에 복사 
           //복사 후   .modal('show')
			e.preventDefault();
           $div=$(this).closest(".row");
           var currentContent=$div.find("pre").html();   //or.text();
           var $modal =$("#id_reply_edit_modal");
          	var $form = $modal.find("form[name='frm_reply_edit']");
          	$form.find("textarea[name='reContent']").val(currentContent);
          	$form.find("input[name='reNo']").val( $div.data("re-no") );
           $modal.modal('show');
        });//수정버튼


        //모달창 저장 버튼
        $("#btn_reply_modify").on("click", function(e){
        	var $modal =$("#id_reply_edit_modal");
          	var $form = $modal.find("form[name='frm_reply_edit']");
          	$.ajax({
          		url:"<c:url value='/reply/replyModify.wow' />"
          		,data: $form.serialize()
          		,success: function(data){
          			console.log(data)
          			$modal.modal("hide");
          			var reNo = $form.find("input[name='reNo']").val();
          			var changeReContent = $form.find("textarea[name='reContent']").val();
					var $div =$("div[data-re-no='" + reNo + "']");
          			$div.find("pre").html(changeReContent);
          		}
          		,error: function(req,st,err){
          			
          		}
          	});
			//가장 가까운form 찾기 , ajax 호출(  data:form.serialzie()
            // 성공 :  modal찾은 후 modal('hide')
            // 현재 모달에 있는 reNo, reContent 찾기
            // 댓글영역에서 re_no에 해당하는 댓글 찾은 후 안의 내용 re_content로 변경
        });//모달창 저장버튼


        //삭제버튼
        $("#id_reply_list_area").on("click", 'button[name="btn_reply_delete"]'
                ,function(e){
			//가장 가까운 div 찾기, 
			//reNo,  reMemId(현재 로그인 한 사람의 id) 구하기
            // ajax 호출(reNo, reMemeId보내기) reMemId는 본인이 쓴 글인지 확인하는데 쓰임 (BizAccessFailException)
	        //성공  후 해당 div.remove 
	        e.preventDefault();
			var $div=$(this).closest(".row");
			var reNo=$div.data("re-no");
			var reMemId='${USER_INFO.userId}'; //session에 있는 userId
			$.ajax({
				url: "<c:url value='/reply/replyDelete.wow' />"
				,data: {"reNo" : reNo, "reMemId" : reMemId}
				,success: function(data){
					$div.remove();
				}
				,error: function(req,st,err){
					
				}
			}); //ajax
        }); //삭제버튼


    });
</script>

</html>






