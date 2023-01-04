<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理系統</title>
</head>
<body>

	<center>
		<div>
			<h3>${message}</h3><br><br><br><br>
			<form action="login" method="post">
			<input type="hidden" name="sno" value="${sno}">
			舊密碼:　<input type="password" name="oldPassword"><br><br>
			新密碼:　<input type="password" name="newPassword"><br><br>
			確認新密碼:　<input type="password" name="confirmNewPassword"><br><br>
			<input type="hidden" name="buttonAction" value="resetPassword">
			<input type="submit">
			</form>
		</div>
	</center>
</body>
</html>