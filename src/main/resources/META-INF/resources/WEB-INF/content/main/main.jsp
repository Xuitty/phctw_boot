<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>學生資料管理系統</title>
  </head>
  <body>
    <center>
      <div>
        <h3>${message}</h3>
        <br /><br /><br /><br />
        學號:　<span id="sno"></span>${sno}　　<br /><br />
        姓名:　<span id="sname"></span>${sname}<br /><br />
        生日:　<span id="sbday"></span>${sbday}　(yyyy-mm-dd)<br /><br />
        性別:　<span id="ssex"></span>${ssex}<br /><br />
        E-mail:　<span id="smail"></span>${smail}<br /><br />
        身分證字號:　${sid}<span id="sid"></span><br /><br /><br /><br />
        <form action="login">
          <input name="sno" type="hidden" value="${sno}" /><input
            name="buttonAction"
            type="hidden"
            value="mainResetPassword"
          /><input value="重設密碼" type="submit" />
        </form>
        <br /><br />
        <input
          type="button"
          value="登出"
          onclick="location.href='/spring/logout';"
        />
      </div>

      <!-- <p><button onmousemove="ajax()">測試</button></p> -->
      <!-- ${sno} -->
    </center>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <!-- <script>
      function ajax() {
        $.ajax({
          url: "getAccountInfo",
          type: "post",
          data: { sno: "ABCD" },
          dataType: "json",
          error: function () {},
          success: function (data) {
            $("#sno").text(data.student.sno);
            $("#sid").text(data.student.sid);
            $("#sname").text(data.student.sname);
            $("#ssex").text(data.student.ssex);
            $("#smail").text(data.student.smail);
            $("#sbday").text(data.student.sbday);
            console.log(data.student.sid);
            if (data != null) {
              location.href = "/spring/test?sno=555";
            }
          },
        });
      }
    </script> -->
  </body>
</html>
