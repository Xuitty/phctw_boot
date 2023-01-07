<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>學生資料管理系統</title>
  </head>
  <script
    src="https://code.jquery.com/jquery-3.4.1.js"
    integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
    crossorigin="anonymous"
  ></script>
  <body>
    <center>
      <div class="container-fluid">
        <div class="row">
          <div class="col-3"></div>
          <div class="col-6">
            <br>
            <h3>歡迎使用學生資料管理系統</h3>
            <br>
            <input
              class="btn btn-primary"
              type="button"
              value="進入系統"
              onclick="location.href='/spring/redirect';"
            />
          </div>
          <div class="col-3"></div>
        </div>
      </div>
    </center>
  </body>
</html>
