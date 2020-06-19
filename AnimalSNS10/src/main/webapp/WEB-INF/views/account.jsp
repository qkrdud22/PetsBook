<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PetsBook Join Page</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
	function success(){
		if($("#id").val() == '' || $("#id").val()==null){
			alert("아이디를 입력하세요");
			return false;
		} else if($("#pw").val() == '' || $("#pw").val()==null){
			alert("비밀번호를 입력하세요");
			return false;
		} else if($("#name").val() == '' || $("#name").val()==null){
			alert("이름을 입력하세요");
			return false;
		} else if($("#email").val() == '' || $("#email").val()==null){
			alert("이메일을 입력하세요")
			return false;
		}else{
			alert("회원가입 완료!!");
			return true;
		}
	}
	
</script>
</head>

<body onload='document.loginForm.username.focus();'>
	<div class="container-login100" style="background-image:url('resources/images/pets.jpg');">
		<div class="wrap-login100 p-t-30- p-b-50">
			<span class="login100-form-title p-b-41">
				Join
			</span>
			<form action="accountOk" method="post" class="login100-form validate-form p-b-33 p-t-5">
				<div class="wrap-input100 validate-input" data-validate = "Enter username">
					<input id="id" class="input100" type="text" name="userid" placeholder="ID" required="required" />
					<span class="focus-input100" data-placeholder="&#xe82a;"></span>
				</div>
				<div class="wrap-input100 validate-input" data-validate="Enter password">
					<input id="pw" class="input100" type="password" name="pwd" placeholder="PASSWORD" required="required" />
					<span class="focus-input100" data-placeholder="&#xe80f;"></span>
				</div>
				<div class="wrap-input100 validate-input">
					<input id="name" class="input100" type="text" name="name" placeholder="NAME" required="required" />
				</div>
				<div class="wrap-input100 validate-input">
					<input id="email" class="input100" type="email" name="email" placeholder="E-MAIL" required="required" />
				</div>
				<input type="hidden" name="username" value="userid" />
				<input type="hidden" name="role" value="ROLE_USER" />
				
				<div class="container-login100-form-btn m-t-32">
				<button class="login100-form-btn" type="submit" id="account" onclick="success()">
					Join
				</button>
				</div>
			</form>
				<input class="btn btn-success btn-sm" type="submit" value="취소하기"
						onclick='post_to_url("${pageContext.request.contextPath}/login")' />
		</div>
	</div>
	
		<div id="dropDownSelect1"></div>
	<!--===============================================================================================-->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/daterangepicker/moment.min.js"></script>
	<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="resources/js/main.js"></script>
</body>
</html>