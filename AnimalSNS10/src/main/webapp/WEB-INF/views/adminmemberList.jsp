<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath }/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath }/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://kit.fontawesome.com/7b6327c064.js" crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/common.js"></script>
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath }/resources/css/agency.min.css" rel="stylesheet" />
<style type="text/css">
/* 여기에 스타일 추가 */
table {
	width: 900px;
	margin: auto;
}

th {
	padding: 5px;
	border: 1px solid gray;
	background: yellow;
	text-align: center;
}

td {
	padding: 5px;
	border: 1px solid gray;
}

.title {
	font-size: 18pt;
	text-align: center;
	border: none;
}

.sub_title {
	font-size: 10pt;
	text-align: right;
	border: none;
}

.subject:hover {
	background-color: silver;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	// 자바 스크립트 추가!!!
	$(function() {

	});
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body id="page-top" style="background-color: #ece6cc;">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"  style="background-color: #ece6cc;">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath }/home">PetsBook</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ml-auto">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link js-scroll-trigger" 
					href="${pageContext.request.contextPath }/admin"><i class="fas fa-user"></i>관리자페이지</a>
					</li>
					</sec:authorize>
					<li class="nav-item">
						<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/video">Pets Video</a>
					</li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath }/boardList">Communication</a></li>
					<!--<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#portfolio">For All</a></li> -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath }/organicanimals">Organic Animals</a></li>
				</ul>
			</div>
			<div style="font-family: 'Sunflower', sans-serif;">동물을 사랑하는 <span style="background-color:yellow;">${pageContext.request.userPrincipal.name}</span>님 환영합니다.
				<a href="javascript:formSubmit()" style="color:#ff7f00 ;"> Logout</a>
			</div>
		</div>
	</nav>
	<!-- navigation 종료 -->
	<section class="page-section" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<table>
						<tr>
							<td colspan="2" class="title">회원 목록</td>
						</tr>
						<tr>
							<th>회원 아이디</th>
							<th>회원 권한</th>
						</tr>
						<c:if test="${empty list }">
							<tr>
								<td colspan="4" align="center">등록된 글이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty list }">
							<c:forEach var="vo" items="${list }" varStatus="vs">
								<tr>
									<td>${vo.username }</td>
									<td>
										<form action="${pageContext.request.contextPath }/admin/memberupdate" method="post">
										<input type="hidden" name="username" value="${vo.username }" />
										<select name = "role">
											<option>현재 : ${vo.role }</option>
											<option value="ROLE_ADMIN">관리자</option>
											<option value="ROLE_USER">일반회원</option>
										</select>
										<button type="submit">
											변경
										</button>
										</form>
									</td>
								</tr>
							</c:forEach>
							<tr>
									<td colspan="2">
										ADMIN = "관리자", USER = "일반회원" 입니다.
									</td>
								</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</section>
</body>
</html>