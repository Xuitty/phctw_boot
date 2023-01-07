<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理系統</title>

</head>
<body>

	<center>
		<div>
		<h2>登入系統</h2>
		<h2 style="color: red">${message}</h2>
			<form action="login" method="post">
				帳號: <input type="text" name="acc"><br> <br> 密碼: <input
					type="password" name="pass"><br> <br> <input
					type="submit" name="buttonAction" value="登入"> <input
					type="submit" name="buttonAction" value="註冊"><br> <br>
			</form>

			<input type="button" value="忘記密碼"
				onclick="location.href='/spring/login?buttonAction=forgetPasswordLogin';"></input>
				<input type="button" value="重發驗證信"
				onclick="location.href='/spring/register?buttonAction=resendLogin';"></input>
		</div>
	</center>
</body>
</html>