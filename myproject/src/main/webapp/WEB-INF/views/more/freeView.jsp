
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
<title>자유게시판</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div class="container">
		<div class="page-header">
			<small> 자유게시판 </small>
		</div>
		<hr>
		<div class="card mb-3">
			<h3 class="card-header">${freeBoard.boTitle }</h3>
			<div class="card-body">
				<h6 class="card-subtitle text-muted">${freeBoard.boWriter }&nbsp;&nbsp;조회수${freeBoard.boHit }
					&nbsp;&nbsp;${freeBoard.boModDate ne null ? freeBoard.boModDate : freeBoard.boRegDate }</h6>
			</div>


			<ul class="list-group list-group-flush">
				<li class="list-group-item">${freeBoard.boContent }<br> 
				<c:forEach	var="f" items="${freeBoard.attaches}" varStatus="st">
						<div>
							<img width="300px" height="280px" src="<%=request.getContextPath()%>/attach/showImg.wow?fileName=${f.atchFileName}&filePath=${f.atchPath}"
								>
						</div>
					</c:forEach>
				</li>

			</ul>
			<div class="card-body">
				<c:if test="${USER_INFO.userName eq freeBoard.boWriter }">
					<a href="freeList.wow" class="card-link">목록</a>
					<a href="freeEdit.wow?boNo=${freeBoard.boNo }" class="card-link">수정</a>
				</c:if>
			</div>
		</div>
	</div>




	<!-- container -->

	<div class="container">
		<!-- reply container -->
		<!-- // START : 댓글 목록 영역  -->
		<div id="id_reply_list_area"></div>
		<!-- // END : 댓글 목록 영역  -->

		<!-- // START : 댓글 등록 영역  -->
		<div class="panel panel-default">
			<div class="panel-body form-horizontal">
				<form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
					method="post" onclick="return false;">
					<input type="hidden" name="reParentNo" value="${freeBoard.boNo}">
					<input type="hidden" name="reCategory" value="FREE"> <input
						type="hidden" name="reMemId" value="${USER_INFO.userId }">
					<hr>
					<div class="form-group" id="txt_area">
						<label for="exampleTextarea" class="form-label mt-4">댓글쓰기</label>
						<textarea class="form-control" name="reContent" rows="3"
							style="resize: none;"
							${USER_INFO eq null ? "readonly='readonly'" : ""}></textarea>
						<button id="btn_reply_regist" type="button"
							class="btn btn-secondary">등록</button>
					</div>
				</form>
			</div>
		</div>
		<!-- // END : 댓글 등록 영역  -->


		<!-- START : 댓글 수정용 Modal -->
		<%-- 		<div class="modal fade" id="id_reply_edit_modal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form name="frm_reply_edit" action="<c:url value='/reply/replyModify' />" method="post" onclick="return false;">
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
							<button id="btn_reply_modify" type="button" class="btn btn-sm btn-info">저장</button>
							<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">닫기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
 --%>
		<div class="modal" id="id_reply_edit_modal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal Content -->
				<div class="modal-content">
					<form name="frm_reply_edit"
						action="<c:url value='/reply/replyModify' />" method="post"
						onclick="return false;">
						<div class="modal-header">
							<h5 class="modal-title">댓글 수정</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true"></span>
							</button>
						</div>
						<div class="modal-body">
							<input type="hidden" name="reNo" value="">
							<textarea rows="3" name="reContent" class="form-control"></textarea>
							<input type="hidden" name="reMemId" value="${USER_INFO.userId }">
						</div>
						<div class="modal-footer">
							<button id="btn_reply_modify" type="button"
								class="btn btn-primary">저장</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
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
        		$.each(list, function(i, elt){
        			replyresult = "";
					replyresult += "<div class='row' data-re-no='"+elt.reNo+"'>";
					replyresult += "<div class='col-sm-2 text-right'>"+elt.reMemName+"</div>";
					replyresult += "<div class='col-sm-6'><pre>"+elt.reContent+"</pre></div>";
					replyresult += "<div class='col-sm-2'>"+elt.reRegDate+"</div>";
					if( elt.reMemId== '${USER_INFO.userId}'){
						replyresult += "<div class='col-sm-2'><button name='btn_reply_edit' type='button' class=' btn btn-outline-primary' >수정</button><button name='btn_reply_delete' type='button' class='btn btn-outline-secondary'>삭제</button></div>";
					}
					replyresult += "</div>";
					$("#id_reply_list_area").prepend(replyresult);
				});
				params.curPage+=1;
        	} //success
        }); //ajax
        
        
    }//function fn_reply_list
    
       
    

    $(document).ready(function(){ //documnet가 준비될 때 
        //더보기 버튼
        fn_reply_list();
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
           $form = $("form[name='frm_reply']"); //이곳의 this는 on("click",function이거
           $.ajax({
	    		url:"<c:url value='/reply/replyRegist.wow'/>"  
	    		,data: $form.serialize()
	    		,success: function(data){
	    			console.log(data);
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
          //$div=$(this).closest(".row");
			$btn=$(this);  //수정버튼
			$div=$btn.closest('div.row');   //버튼의 댓글 div
			$modal=$('#id_reply_edit_modal'); //modal div 
			$pre=$div.find('pre'); 
			 var content=$pre.html(); 
			 $textarea=$modal.find('textarea'); 
			
			 $textarea.val(content);  
			 var reNo=$div.data('re-no');	
			 $modal.find('input[name=reNo]').val(reNo);
			 $modal.modal('show');
		});//수정버튼


		//모달창 저장 버튼
		$("#btn_reply_modify").on("click", function(e){
			e.preventDefault(); 
			$form= $(this).closest('form[name="frm_reply_edit"]');
			$.ajax({
				url : "<c:url value='/reply/replyModify.wow' />"
				,type : "POST"
				,data : $form.serialize()
				,dataType : "JSON"
				,success: function(){
					$modal=$('#id_reply_edit_modal'); 
					$modal.modal('hide');
					
					var reNo=$modal.find('input[name=reNo]').val();
					var reContent=$modal.find('textarea').val();
					$("#id_reply_list_area").find("div[data-re-no='"+reNo+"']").find("pre").html(reContent);
				}
			});//ajax
			//가장 가까운form 찾기 , ajax 호출(  data:form.serialzie()
            // 성공 :  modal찾은 후 modal('hide')
            // 현재 모달에 있는 reNo, reContent 찾기
            // 댓글영역에서 re_no에 해당하는 댓글 찾은 후 안의 내용 re_content로 변경
        });//모달창 저장버튼


    	//삭제버튼
    	$("#id_reply_list_area").on("click", 'button[name="btn_reply_delete"]'
    			,function(e){
    		e.preventDefault();
    		$div=$(this).closest('.row');
    		reNo=$div.data('re-no');
    		reMemId="${USER_INFO.userId}";
    		$.ajax({
    			url : "<c:url value='/reply/replyDelete.wow' />"
    			,type : "POST"
    			,data : {"reNo" : reNo, "reMemId" : reMemId}
    			,dataType : 'JSON'
    			,success : function(){
    				$div.remove();
    			}
    		});//ajax
    	}); //삭제버튼
    });
</script>

</html>






