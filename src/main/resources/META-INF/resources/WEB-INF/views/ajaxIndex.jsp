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

<form>
    学号：<input type="text" id="no"><br />
    姓名：<input type="text" id="name"><br />
    <button type="button" id="btn">查询成绩</button>
  </form>
  <div id="score"></div>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script>
    $("#no").keyup(function () {
      $.ajax({
        url:"ajax",   //请求地址
        type:"get",   //请求方法
        data:{"no":$("#no").val(),"name":$("#name").val()},   //要发送的数据,相当于表单提交的数据，json形式。
        dataType:"text",   //期待返回的数据类型，也可以理解为请求的数据类型
        error:function () {  //发生错误时的处理

        },
        success:function (data) {  //成功时的处理。参数表示返回的数据
          $("#score").text(data);
        }
      })
    });
  </script>

</center>
</body>
</html>