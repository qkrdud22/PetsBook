<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commVO.tableName}</title>
<!-- Bootstrap core CSS -->
<link href="resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template -->
<link href="resources/vendor/fontawesome-free/css/all.min.css"
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
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://kit.fontawesome.com/7b6327c064.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/common.js"></script>
<!-- Custom styles for this template -->
<link href="resources/css/agency.min.css" rel="stylesheet" />

<style type="text/css">
/* 여기에 스타일 추가 */
table {
	width: 900px;
	margin: auto;
}

th {
	padding: 5px;
	border: 1px solid gray;
	background: silver;
	text-align: center;
}

td {
	padding: 5px;
	border: 0px solid gray;
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
	
	function formCheck(){
		var data = $("#subject").val();
		if(isNullCheck(data)){
			alert('제목은 반드시 입력해야 합니다.');
			$("#subject").val("");
			$("#subject").focus();
			return false;
		}
		var data = $("#content").val();
		if(isNullCheck(data)){
			alert('내용은 반드시 입력해야 합니다.');
			$("#content").val("");
			$("#content").focus();
			return false;
		}
		return true;
	}	
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>

<body style="background-color: #ece6cc;">
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
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath }/boardList">Communication</a></li>
					<!--<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#portfolio">For All</a></li> -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#contact">Organic Animals</a></li>
				</ul>
			</div>
			<div style="font-family: 'Sunflower', sans-serif;">동물을 사랑하는 <span style="background-color:yellow;">${pageContext.request.userPrincipal.name}</span>님 환영합니다.
				<a href="javascript:formSubmit()" style="color:#ff7f00 ;"> Logout</a>
			</div>
		</div>
	</nav>
	<!-- navigation 종료 -->
	<form action="updateOk" method="post" onsubmit="return formCheck();"
		enctype="multipart/form-data">
		<%-- 몇가지는 숨겨서 가자!!! --%>
		<input type="hidden" name="idx" value="${vo.idx }"> <input
			type="hidden" name="p" value="${commVO.currentPage }"> <input
			type="hidden" name="s" value="${commVO.pageSize }"> <input
			type="hidden" name="b" value="${commVO.blockSize }"> <input
			type="hidden" name="tb" value="${commVO.tableName }" /> <input
			type="hidden" name="ip" value="${pageContext.request.remoteAddr }">
		<section class="page-section" id="about">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-center">
						<table style="border: 1px; text-align: center;">
							<tr>
								<td colspan="4" class="title">For the ${commVO.tableName}</td>
							</tr>
							<tr>
								<td align="right" width="10%">이름</td>
								<td width="40%" align="left"><input type="hidden"
									name="name" value="${pageContext.request.userPrincipal.name}"
									readonly="readonly"> ${vo.name }</td>
							</tr>
							<tr>
								<td align="right" valign="top">제목</td>
								<td align="left" colspan="3"><input type="text"
									name="subject" id="subject" required="required"
									placeholder="제목입력" size="78" value="${vo.subject }"></td>
							</tr>
							<tr>
								<td align="right" valign="top">내용</td>
								<td align="left" colspan="3">
								<c:if test="${not empty vo.image }">
								<img src="${pageContext.request.contextPath }/upload/${vo.image }"
									width="200" height="200" /> 
								</c:if>
								<textarea rows="10" cols="80"
										name="content" required="required" id="content">${vo.content }</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="right" style="padding-right: 120px;">
									<p>파일을 새로 올리시면 사진이 변경됩니다.</p> <input type="file" name="file">
									<input type="submit" value="수정하기"
									class="btn btn-outline-primary btn-sm"> <input
									type="button" value="취소하기" class="btn btn-outline-dark btn-sm"
									onclick='post_to_url("${pageContext.request.contextPath }/view",{"tb":"${commVO.tableName}","idx":${vo.idx }, "m":"0", "p":${commVO.currentPage },"s":${commVO.pageSize},"b":${commVO.blockSize }})'>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</section>
	</form>
</body>
</html>