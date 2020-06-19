<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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

<script type="text/javascript">
	// 자바 스크립트 추가!!!
	$(function() {

	});
	function doNotReload(){
	    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
	        event.keyCode = 0;
	        event.cancelBubble = true;
	        event.returnValue = false;
	    } 
	}
	document.onkeydown = doNotReload;
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
<style>
th {padding: 5px; border: 1px solid white; background: white;text-align: center;}
td {padding: 5px; border: 1px solid white;}
.subject:hover{
	boader:1px solid white;
	background:yellow;
	cursor: pointer
}
.realsubject{
  width        : 700px;     /* 너비는 변경될수 있습니다. */
  text-overflow: ellipsis;  /* 위에 설정한 100px 보다 길면 말줄임표처럼 표시합니다. */
  white-space  : nowrap;    /* 줄바꿈을 하지 않습니다. */
  overflow     : hidden;    /* 내용이 길면 감춤니다 */
  display      : block;     /* ie6이상 현재요소를 블럭처리합니다. */
}
.title {font-size: 18pt; text-align: center; border: none;}
.sub_title {font-size: 10pt; text-align: center; border: none;}
</style>
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
		<button class="btn btn-success btn-sm"
			onclick='post_to_url("${pageContext.request.contextPath }/mainInsert",),{"p":${commVO.currentPage} ,"s": ${commVO.pageSize }, "b":${commVO.blockSize }}'>타임라인
			글쓰기
		</button>
	</nav>
	<!-- navigation 종료 -->
	<form action="index" method="post" onsubmit="return formCheck();"
		enctype="multipart/form-data">
		<%-- 몇가지는 숨겨서 가자!!! --%>
		<input type="hidden" name="p" value="1"> <input type="hidden"
			name="s" value="${commVO.pageSize }"> <input type="hidden"
			name="b" value="${commVO.blockSize }"> <input type="hidden"
			name="tb" value="${commVO.tableName }"> <input type="hidden"
			name="ip" value="${pageContext.request.remoteAddr }">
	</form>
	<section class="page-section" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<table width="1000px" style="margin:0 auto;">
						<tr>
							<td colspan="5" class="title">${commVO.tableName}</td>
						</tr>
						<tr>
							<td colspan="5" class="sub_title">${pagingVO.pageInfo }</td>
						</tr>
						<tr>
							<th>번호</th>
							<th width="40%">제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
						<c:if test="${pagingVO.totalCount eq 0 }">
							<tr>
								<td colspan="5" align="center">등록된 글이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${pagingVO.totalCount gt 0 }">
							<c:set var="no"
								value="${pagingVO.totalCount-(pagingVO.currentPage-1)*pagingVO.pageSize }" />
							<c:forEach var="vo" items="${pagingVO.list }" varStatus="vs">
								<tr align="center" class="subject"
									onclick='post_to_url("view",{"tb":"${commVO.tableName}","idx":${vo.idx },"m":"1" ,"p":${commVO.currentPage} ,"s": ${commVO.pageSize }, "b":${commVO.blockSize }})'>
									<td>${no-vs.index}</td>
									<td align="left" class="realsubject">
										<c:out value="${vo.subject }" /> 
										<c:if test="${vo.commentCount > 0 }">
											(${vo.commentCount })
										</c:if>
									</td>
									<td><c:out value="${vo.name }" /></td>
									<td><fmt:formatDate value="${vo.regDate }"
											pattern="yyyy-MM-dd" /></td>
									<td>${vo.hit }</td>
								</tr>
							</c:forEach>
							<%-- 페이지 이동 --%>
							<tr>
								<td colspan="5" style="border: none;">
									${pagingVO.pageListPost }</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="5" style="border: none; text-align: right;">
								<button class="btn btn-success btn-sm"
									onclick='post_to_url("${pageContext.request.contextPath }/insert",{"tb":"${commVO.tableName}","p":${commVO.currentPage} ,"s": ${commVO.pageSize }, "b":${commVO.blockSize }})'>글쓰기</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</section>
</body>
</html>








