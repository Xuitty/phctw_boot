<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理系統</title>
</head>
<body>
	<center>
		<h2>請至您的信箱收取驗證碼完成註冊程序</h2>
		<br>
		<div>
			<form action="register" method="post">


				<br> 驗證碼:<input type="text" name="verify"><br> <br>
				<input type="hidden" name="buttonAction" value="verify"> 
				<input type="hidden" name="sno" value="${sno}"> 
				<input type="submit" value="送出">
			</form>

		</div>
	</center>
</body>
</html>