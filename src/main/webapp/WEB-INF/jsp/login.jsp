<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号登录</title>
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/iconfont.css" />
</head>

<body>
	<div class="login">
		<span id="span01">铜陵有色OA</span><span id="span02"><a
			href="register.do">用户注册</a></span>
		<form id="loginForm" action="shiroLogin.do" method="post">
			<div class="">
				<i class="iconfont icon-denglu"></i> <input type="text"
					id="userName" name="userName" placeholder="请输入账号"
					onblur="getMsg(this.value)" />
			</div>
			<div class="">
				<i class="iconfont icon-mima"></i> <input type="password"
					id="passwd" name="passwd" placeholder="请输入密码" />
			</div>
			<div class="">
				<input type="button" value="登 录" id="sub" />
			</div>
			<div class="">
				<input type="checkbox" value="1" id="keepPwd" name="keepPwd">
				<span id="span03">记住密码</span> <span id="span02"><a href="#">忘记密码</a></span>
			</div>
		</form>
	</div>

	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		//查询cook值，用户登录记录
		var cook = document.cookie;
		function getMsg(account) {
			var cookArr = cook.split("; ");
			for (var i = 0; i < cookArr.length; i++) {
				var arr = cookArr[i].split("=");
				if (arr[0] == account) {
					document.getElementById("pwd").value = arr[1];
				}
			}
		}
		$("#sub").click(function() {
			var phone = $("#phone").val();
			var pwd = $("#pwd").val();
			if (phone != "" && pwd != "") {
				$("#loginForm").submit();
			} else {
				alert("请将信息填写完整！");
			}
		});
	</script>
</body>
</html>