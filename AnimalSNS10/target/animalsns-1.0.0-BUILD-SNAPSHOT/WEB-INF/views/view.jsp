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
	border: 0px solid gray;
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
</style>
<script type="text/javascript">
	// 자바 스크립트 추가!!!
	$(function() {

	});
	
	// 댓글 수정
	function CommentUpdate(idx){
		var name = $("#name"+idx).text();
		var content = $("#content"+idx).text();
		$("#commentForm").attr("action","CommentUpdateOk");
		$("#idx").val(idx);
		$("#name").val(name);
		$("#content").val(content);
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val("수정");
	}
	// 댓글 삭제
	function CommentDelete(idx){
		var name = $("#name"+idx).text();
		var content = $("#content"+idx).text();
		$("#commentForm").attr("action","CommentDeleteOk");
		$("#idx").val(idx);
		$("#name").val(name);
		$("#content").val(content);
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val("삭제");
	}
	// 폼 리셑
	function commentReset(){
		$("#commentForm").attr("action","CommentInsertOk");
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
	<section class="page-section" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<table style="border: 1px; text-align: center;">
						<tr>
							<td colspan="6" class="title">For the ${commVO.tableName}</td>
						</tr>
						<tr>
							<td align="right" width="10%">이름 :</td>
							<td width="30%" align="left"><c:out value="${vo.name }" /></td>
							<td align="right" width="10%">작성일 :</td>
							<td width="30%" align="left"><fmt:formatDate
									value="${vo.regDate }" pattern="yyyy년 MM월 dd일 hh:mm:ss " /></td>
							<td align="right" width="10%">조회수 :</td>
							<td width="10%" align="left">${vo.hit }</td>
						</tr>
						<tr>
							<td align="right" valign="top">제목 :</td>
							<td align="left" colspan="5"><c:out value="${vo.subject }" />
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">내용 :</td>
							<td align="left" colspan="5"
								style="border: 1px solid gray; padding: 5px;"><c:if
									test="${not empty vo.image }">
									<img
										src="${pageContext.request.contextPath }/upload/${vo.image }"
										width="200" height="200" />
								</c:if> <c:set var="content" value="${vo.content }" /> <c:set
									var="content" value="${fn:replace(content,'<','&nbsp;') }" /> <c:set
									var="content" value="${fn:replace(content, newLine,br) }" />
								${content }</td>
						</tr>
						<tr>
							<td colspan="6" align="right"><input type="button"
								value="목록으로" class="btn btn-outline-dark btn-sm"
								onclick='post_to_url("${pageContext.request.contextPath }/index",{"tb":"${commVO.tableName}","p":${commVO.currentPage },"s":${commVO.pageSize},"b":${commVO.blockSize }})'>
								<c:if test="${pageContext.request.userPrincipal.name == vo.name}">
									<input type="button" value="수정하기"
										class="btn btn-outline-dark btn-sm"
										onclick='post_to_url("${pageContext.request.contextPath }/update",{"tb":"${commVO.tableName}","idx":${vo.idx },"m":0, "p":${commVO.currentPage },"s":${commVO.pageSize},"b":${commVO.blockSize }})'>
									<input type="button" value="삭제하기"
										class="btn btn-outline-dark btn-sm"
										onclick='post_to_url("${pageContext.request.contextPath }/delete",{"tb":"${commVO.tableName}","idx":${vo.idx },"m":0, "p":${commVO.currentPage },"s":${commVO.pageSize},"b":${commVO.blockSize }})'>
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="6"
								style="background-color: gold; text-align: left; border: 5px solid gray">
								<%-- 댓글 폼 --%>
								<form action="CommentInsertOk" method="post" id="commentForm">
									<input type="hidden" name="idx" id="idx" value="-1"> <input
										type="hidden" name="ref" id="ref" value="${vo.idx }">
									<input type="hidden" name="p" value="${commVO.currentPage }">
									<input type="hidden" name="s" value="${commVO.pageSize }">
									<input type="hidden" name="b" value="${commVO.blockSize }">
									<input type="hidden" name="tb" value="${commVO.tableName }" />
									<input type="hidden" name="m" value="0"> <input
										type="hidden" name="name" id="name"
										value="${pageContext.request.userPrincipal.name}">
									${pageContext.request.userPrincipal.name} <br>
									<textarea rows="2" cols="90" name="content" id="content"></textarea>
									<br> <input type="submit" value="댓글저장" id="submitBtn">
									<input type="button" value="취소" id="cancelBtn"
										style="display: none;" onclick="commentReset()">
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="6"><c:if test="${empty vo.commentList }">
				등록된 댓글이 없습니다.
			</c:if> <c:if test="${not empty vo.commentList }">
									<div
										style="border-bottom: 2px dotted skyblue; text-align: left;">
										전체 ${fn:length(vo.commentList) }개의 댓글이 있습니다.</div>
									<c:forEach var="comment" items="${vo.commentList }">
										<div style="border-bottom: 1px dotted pink; text-align: left;">
											<strong> <span id="name${comment.idx }">${comment.name }</span>씨가
												${comment.ip }에서 <fmt:formatDate value="${comment.regDate }"
													pattern="yyyy-MM-dd hh:mm:ss" />에 남긴글 <c:if
													test="${pageContext.request.userPrincipal.name == comment.name}">
													<button class="btn btn-success btn-sm"
														onclick="CommentUpdate('${comment.idx }')"><i class="fas fa-wrench"></i></button>
													<button class="btn btn-success btn-sm"
														onclick="CommentDelete('${comment.idx }')"><i class="fas fa-trash-alt"></i></button>
												</c:if>
											</strong>
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
								</c:if></td>
						</tr>

					</table>
				</div>
			</div>
		</div>
	</section>
</body>
</html>