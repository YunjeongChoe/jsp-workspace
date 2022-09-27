<%-- <%@page import="com.study.login.vo.UserVO"%> --%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Fixed navbar -->

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Veganism</a>
		${properties }
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link active"
					href="<%=request.getContextPath()%>/">Home <span
						class="visually-hidden">(current)</span>
				</a></li>
				
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/vege/recipe.wow">Recipe</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/about/test.wow">About</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">MORE</a>
					<div class="dropdown-menu">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/more/freeList.wow">게시판</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="<%=request.getContextPath()%>/more/map.wow">맛집탐방</a>
						<a class="dropdown-item" href="<%=request.getContextPath()%>/more/javas.wow">자바스크립트</a>
						<a class="dropdown-item" href="<%=request.getContextPath()%>/more/jqry.wow">제이쿼리</a>
					</div></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">


				<c:if test="${USER_INFO eq null }">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/login/login.wow">로그인</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/join/step1.wow">회원가입</a></li>
				</c:if>
				
				<c:if test="${USER_INFO ne null }">
					<li class="dropdown"><a class="nav-link dropdown-toggle"
						data-bs-toggle="dropdown" href="#" role="button"
						aria-haspopup="true" aria-expanded="false">${USER_INFO.userName }님
							<span class="caret"></span>
					</a>
						<div class="dropdown-menu" style="">

							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/mypage/info.wow"> <span
								class="glyphicon glyphicon-home" aria-hidden="true"></span>
								&nbsp;&nbsp;My page
							</a> <a class="dropdown-item" href="#"> <span class="glyphicon glyphicon-cog"
								aria-hidden="true"></span> &nbsp;&nbsp;비밀번호 변경
							</a> <a class="dropdown-item" href="#"> <span class="glyphicon glyphicon-th-list"
								aria-hidden="true"></span> &nbsp;&nbsp;1:1 문의게시판
							</a>
							 <div class="dropdown-divider"></div>
							<a class="dropdown-item" 
								href="<%=request.getContextPath()%>/login/logout.wow"> <span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								&nbsp;&nbsp;로그아웃
							</a>

						</div></li>
				</c:if>
			</ul>
			<form class="d-flex">
				<input class="form-control me-sm-2" type="text" placeholder="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>

		</div>
	</div>
</nav>


<br>
<br>
<br>
<br>
<br>
<br>




