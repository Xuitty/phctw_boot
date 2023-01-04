<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>學生資料管理系統</title>
  </head>
  <body id="body">
    <center>
      <h2 style="color: red">${message}</h2>
      <h2>重發驗證信請填帳號信箱<br /><br />收信後填入帳號跟驗證碼</h2>
      <br />
      <div>
        <form action="register" method="post">
          <br />
          帳號(學號):<input type="text" id="sno" name="sno" />　*必填 <br />
          <br />
          新的信箱:<input
            type="text"
            id="smail"
            name="smail"
          />　*重傳必填<br />
          <br />
          驗證碼:<input
            type="text"
            id="verify"
            name="verify"
          />　*驗證必填<br />
          <br />
          <input
            id="buttonVerify"
            type="submit"
            name="buttonAction"
            value="verify"
          />
          　　
          <input
            id="buttonResend"
            type="submit"
            name="buttonAction"
            value="resend"
          /><br />
          <br />
          <br />
          <br />
        </form>
      </div>
    </center>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script>
      var a, b, c;
      document.getElementById("smail").setAttribute("disabled", "");
      document.getElementById("verify").setAttribute("disabled", "");
      document.getElementById("buttonVerify").style.display = "none";
      document.getElementById("buttonResend").style.display = "none";
      $("#body").keyup(function () {
        detect();
      });

      $("#body").mousedown(function () {
        detect();
      });

      function detect() {
        a = $("#sno").val().length;
        b = $("#smail").val().length;
        c = $("#verify").val().length;
        document.getElementById("verify").removeAttribute("disabled");
        document.getElementById("smail").removeAttribute("disabled");
        if (a == 0) {
          document.getElementById("smail").setAttribute("disabled", "");
          document.getElementById("verify").setAttribute("disabled", "");
        }
        if (a > 0) {
          document.getElementById("buttonVerify").style.display = "none";
          document.getElementById("buttonResend").style.display = "none";
          if (b > 0) {
            document.getElementById("verify").setAttribute("disabled", "");
            document.getElementById("buttonResend").style.display = "block";
            document.getElementById("buttonVerify").style.display = "none";
          }
          if (c > 0) {
            document.getElementById("smail").setAttribute("disabled", "");
            document.getElementById("buttonVerify").style.display = "block";
            document.getElementById("buttonResend").style.display = "none";
          }
        }
      }
    </script>
  </body>
</html>
