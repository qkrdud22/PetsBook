<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>PetsBook</title>
<style>
table {
	border: 1px solid white;
	text-align: center;
}

#hit {
	
}

.divimage {
	width: 1000px;
	height: 600px;
	background-size: cover;
}
</style>
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
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://kit.fontawesome.com/7b6327c064.js" crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/common.js"></script>
<!-- Custom styles for this template -->
<link href="resources/css/agency.min.css" rel="stylesheet" />
<script type="text/javascript">
	// 자바 스크립트 추가!!!
	$(function() {

	});
	
	// 댓글 수정
	function MainCommentUpdate(idx){
		var name = $("#name"+idx).text();
		var content = $("#content"+idx).text();
		$("#commentForm").attr("action","mainCommentUpdateOk");
		$("#idx").val(idx);
		$("#name").val(name);
		$("#content").val(content);
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val("수정");
	}
	// 댓글 삭제
	function MainCommentDelete(idx){
		var name = $("#name"+idx).text();
		var content = $("#content"+idx).text();
		$("#commentForm").attr("action","mainCommentDeleteOk");
		$("#idx").val(idx);
		$("#name").val(name);
		$("#content").val(content);
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val("삭제");
	}
	// 폼 리셑
	function MainCommentReset(){
		$("#commentForm").attr("action","mainCommentInsertOk");
		$("#idx").val(-1);
		$("#name").val("");
		$("#content").val("");
		$("#cancelBtn").css("display","none");
		$("#submitBtn").val("저장");
	}
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
	
</script>
</head>
<body id="page-top"  style="background-color: #ece6cc;">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"  style="background-color: #ece6cc;">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="#page-top">PetsBook</a>
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
						href="${pageContext.request.contextPath }/organicanimals">Organic Animals</a></li>
				</ul>
			</div>
			<div style="font-family: 'Sunflower', sans-serif;">동물을 사랑하는 <span style="background-color:yellow;">${pageContext.request.userPrincipal.name}</span>님 환영합니다.
				<a href="javascript:formSubmit()" style="color:#ff7f00 ;"> Logout</a>
			</div>
		</div>
		<button class="btn btn-success btn-sm"
			onclick='post_to_url("${pageContext.request.contextPath }/mainInsert",),{"p":${commVO.currentPage} ,"s": ${commVO.pageSize }, "b":${commVO.blockSize }}'>타임라인
			글쓰기
		</button>
	</nav>
	<!-- navigation 종료 -->
	<section class="page-section" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading text-uppercase">PetsBook</h2>
					<h3 class="section-subheading text-muted"></h3>
					<table>
					<c:forEach var="vo" items="${pagingVO.list }" varStatus="vs">
						<tbody style="background:#ecee6cc; margin-bottom:8rem;">
							<c:if test="${pagingVO.totalCount gt 0 }">
								<tr>
									<td width="20%" align="left" style="font-size: 30px; font-family: 'Sunflower', sans-serif;" >
										<strong><i class="fas fa-paw"></i><c:out value="${vo.name}" /></strong>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="5" style=padding:"5px;">
										<c:if test="${not empty vo.image }">
											<div class="divimage" style="background-image: url('${pageContext.request.contextPath }/upload/${vo.image }'); background-size: cover; background-repeat: no-repeat;"></div>
										</c:if>
										<br /> 
										<c:set var="content" value="${vo.content }" /> 
										<c:set var="content" value="${fn:replace(content,'<','&nbsp;') }" />
										<c:set var="content" value="${fn:replace(content, newLine,br) }" /> ${content }
									</td>
								</tr>
								<tr>
									<td align="right" width="5%">작성일 :
									<fmt:formatDate value="${vo.regDate }" pattern="yyyy년 MM월 dd일 hh:mm:ss " />
									</td>
									<!-- <td width="30%" align="left">
									</td> -->
									<%-- <td align="left" width="5%" id="hit">조회수 :</td>
									<td width="10%" align="left">${vo.hit }</td> --%>
									<c:if test="${pageContext.request.userPrincipal.name == vo.name}">
										<td colspan="6" align="right">
										<input type="button" value="수정" class="btn btn-outline-dark"
											onclick='post_to_url("mainupdate",{"idx":${vo.idx },"m":0, "p":${commVO.currentPage },"s":${commVO.pageSize},"b":${commVO.blockSize }})'>
											<input type="button" value="삭제"
											class="btn btn-outline-dark"
											onclick='post_to_url("maindelete",{"idx":${vo.idx },"m":0, "p":${commVO.currentPage },"s":${commVO.pageSize},"b":${commVO.blockSize }})'>
										</td>
									</c:if>
								</tr>
								<tr>
									<td colspan="6"
										style="background-color: white; text-align: left; border: 5px solid gray; width:800px;">
										<%-- 댓글 폼 --%>
										<form action="mainCommentInsertOk" method="post" id="commentForm">
											<input type="hidden" name="idx" id="idx" value="-1">
											<input type="hidden" name="ref" id="ref" value="${vo.idx }">
											<input type="hidden" name="p" value="${commVO.currentPage }">
											<input type="hidden" name="s" value="${commVO.pageSize }">
											<input type="hidden" name="b" value="${commVO.blockSize }">
											<input type="hidden" name="m" value="0"> 
											<input type="hidden" name="ip" value="${pageContext.request.remoteAddr }">
											<input type="hidden" name="name" id="name"
												value="${pageContext.request.userPrincipal.name}">
											<textarea rows="1" cols="80" name="content" id="content"></textarea>
											<br> <input type="submit" value="댓글저장" id="submitBtn">
											<input type="button" value="취소" id="cancelBtn"
												style="display: none;" onclick="MainCommentReset()">
										</form>
									</td>
								</tr>
								</c:if>
								<tr>
									<td colspan="6">
										<c:if test="${empty vo.commentList }">
											댓글을 입력해 소통해보세요
										</c:if>
										<c:if test="${not empty vo.commentList }">
										<div style="border-bottom: 2px dotted skyblue; text-align: left;">
											전체 ${fn:length(vo.commentList) }개의 댓글이 있습니다.
										</div>
										<c:forEach var="comment" items="${vo.commentList }">
										<div style="border-bottom: 1px dotted pink; text-align: left;">
											<strong><span id="name${comment.idx }">${comment.name }</span> 님이
												${comment.ip }에서 <fmt:formatDate
													value="${comment.regDate }" pattern="yyyy-MM-dd hh:mm:ss" />
												에 남긴글 <c:if
													test="${pageContext.request.userPrincipal.name == comment.name}">
													<button class="btn btn-outline-dark"
														onclick="MaincommentUpdate('${comment.idx }')"><i class="fas fa-wrench"></i></button>
													<button class="btn btn-outline-dark"
														onclick="MaincommentDelete('${comment.idx }')"><i class="fas fa-trash-alt"></i></button>
												</c:if> </strong>
											<div id="content${comment.idx }" style="display: none;">${comment.content }</div>
											<div>
												<c:set var="content2" value="${comment.content }" />
												<c:set var="content2"
													value="${fn:replace(content2,'<','&nbsp;') }" />
												<c:set var="content2"
													value="${fn:replace(content2, newLine,br) }" />
												${content2 }
											</div>
										</div>
										</c:forEach>										
										</c:if>
										<br />
										<br />
										<br />
										<br />
										<br />
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</section>
	<!-- Portfolio Modals -->
	<!-- Bootstrap core JavaScript -->
	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Contact form JavaScript -->
	<script src="resources/js/jqBootstrapValidation.js"></script>
	<script src="resources/js/contact_me.js"></script>

	<!-- Custom scripts for this template -->
	<script src="resources/js/agency.min.js"></script>
</body>
</html>
